package com.wind.LogsToRedis_329.serilogj.sinks.seq;

import com.wind.LogsToRedis_329.serilogj.core.LoggingLevelSwitch;
import com.wind.LogsToRedis_329.serilogj.debugging.SelfLog;
import com.wind.LogsToRedis_329.serilogj.events.LogEvent;
import com.wind.LogsToRedis_329.serilogj.events.LogEventLevel;
import com.wind.LogsToRedis_329.serilogj.formatting.ITextFormatter;
import com.wind.LogsToRedis_329.serilogj.formatting.json.JsonFormatter;
import com.wind.LogsToRedis_329.serilogj.sinks.periodicbatching.PeriodicBatchingSink;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class SeqSink extends PeriodicBatchingSink {
	public static final int DefaultBatchPostingLimit = 1000;
	public static final Duration DefaultPeriod = Duration.ofSeconds(2);
	private static final Duration RequiredLevelCheckInterval = Duration.ofMinutes(2);
	private static final String BulkUploadResource = "api/events/raw";
	private static final String ApiKeyHeaderName = "X-Seq-ApiKey";

	private final URL baseUrl;
	private final Long eventBodyLimitBytes;
	private LoggingLevelSwitch levelSwitch;
	private LocalDateTime nextRequiredLevelCheck = LocalDateTime.now().plus(RequiredLevelCheckInterval);
	private final Map<String, String> httpHeaders;

	public SeqSink(String serverUrl, String apiKey, Integer batchSizeLimit, Duration period, Long eventBodyLimitBytes,
				LoggingLevelSwitch levelSwitch) {
		this(serverUrl, apiKey, null, batchSizeLimit, period, eventBodyLimitBytes, levelSwitch);
	}

	public SeqSink(String serverUrl, String apiKey, Map<String,String> customHttpHeaders, Integer batchSizeLimit, Duration period, Long eventBodyLimitBytes,
			LoggingLevelSwitch levelSwitch) {
		super(batchSizeLimit == null ? DefaultBatchPostingLimit : batchSizeLimit,
				period == null ? DefaultPeriod : period);

		if (serverUrl == null) {
			throw new IllegalArgumentException("serverUrl");
		}

		if (!serverUrl.endsWith("/")) {
			serverUrl += "/";
		}
		this.eventBodyLimitBytes = eventBodyLimitBytes;
		this.levelSwitch = levelSwitch;

		try {
			baseUrl = new URL(serverUrl + BulkUploadResource);
		} catch (MalformedURLException e) {
			SelfLog.writeLine("Invalid server url format: %s", serverUrl + BulkUploadResource);
			throw new IllegalArgumentException("serverUrl");
		}

		Map<String, String> httpHeaders = new HashMap<>();
		httpHeaders.put("Content-Type", "application/json; charset=utf-8");
		if(customHttpHeaders != null) {
			httpHeaders.putAll(customHttpHeaders);
		}
		if (apiKey != null && !apiKey.equals("")) {
			httpHeaders.put(ApiKeyHeaderName, apiKey);
		}
		this.httpHeaders = Collections.unmodifiableMap(httpHeaders);
	}

	@Override
	protected void emitBatch(Queue<LogEvent> events) {
		nextRequiredLevelCheck = LocalDateTime.now().plus(RequiredLevelCheckInterval);

		StringWriter payload = new StringWriter();
		payload.write("{\"Events\":[");

		ITextFormatter formatter = new JsonFormatter(false, "", false, null);
		String delimStart = "";
		try {
			for (LogEvent logEvent : events) {
				if (eventBodyLimitBytes != null) {
					StringWriter buffer = new StringWriter();
					formatter.format(logEvent, buffer);
					String buffered = buffer.toString();

					if (buffered.length() > eventBodyLimitBytes) {
						SelfLog.writeLine(
								"Event JSON representation exceeds the byte size limit of %d set for this sink and will be dropped; data: %s",
								eventBodyLimitBytes, buffered);
					} else {
						payload.write(delimStart);
						formatter.format(logEvent, payload);
						delimStart = ",";
					}
				} else {
					payload.write(delimStart);
					formatter.format(logEvent, payload);
					delimStart = ",";
				}
			}
		} catch (IOException e) {
			// Never happens
		}

		payload.write("]}");

		try {
			HttpURLConnection con = (HttpURLConnection) baseUrl.openConnection();
			con.setRequestMethod("POST");
			httpHeaders.forEach(con::setRequestProperty);
			con.setDoOutput(true);

			OutputStream os = con.getOutputStream();
			os.write(payload.toString().getBytes("UTF8"));
			os.flush();
			os.close();

			InputStream stream;
			int responseCode = con.getResponseCode();
			if (responseCode < 200 || responseCode >= 300) {
				stream = con.getErrorStream();
			} else {
				stream = con.getInputStream();
			}

			String line = "";
			String response = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(stream, "UTF8"));
			while ((line = in.readLine()) != null) {
				response += line;
			}
			in.close();

			if (responseCode < 200 || responseCode >= 300) {
				throw new IOException(response);
			}

			LogEventLevel level = SeqApi.readEventInputResult(response);
			if (level != null && levelSwitch == null) {
				levelSwitch = new LoggingLevelSwitch(level);
			}
		} catch (IOException e) {
			SelfLog.writeLine("Error sending events to Seq, exception %s", e.getMessage());
		}
	}

	@Override
	protected void onEmptyBatch() {
		if (levelSwitch != null && nextRequiredLevelCheck.isBefore(LocalDateTime.now())) {
			emitBatch(new LinkedList<LogEvent>());
		}
	}
}
