package com.wind.LogsToRedis_329.serilogj.sinks.rollingfile;

import com.wind.LogsToRedis_329.serilogj.core.ILogEventSink;
import com.wind.LogsToRedis_329.serilogj.debugging.SelfLog;
import com.wind.LogsToRedis_329.serilogj.events.LogEvent;
import com.wind.LogsToRedis_329.serilogj.formatting.ITextFormatter;

import java.io.*;

public class FileSink implements ILogEventSink, Closeable {
	private String filename;
	private FileWriter fileWriter;
	private Writer output;
	private Long fileSizeLimitBytes;
	private boolean buffered;
	private ITextFormatter formatter;
	private Object syncRoot = new Object();

	public FileSink(String path, Long fileSizeLimitBytes, boolean buffered, ITextFormatter formatter)
			throws IOException {
		if (formatter == null) {
			throw new IllegalArgumentException("formatter");
		}
		if (path == null) {
			throw new IllegalArgumentException("path");
		}
		if (fileSizeLimitBytes != null && fileSizeLimitBytes < 0) {
			throw new IllegalArgumentException("fileSizeLimitBytes");
		}

		this.fileSizeLimitBytes = fileSizeLimitBytes;
		this.formatter = formatter;
		this.buffered = buffered;
		filename = path;

		tryCreateDirectory(path);

		output = fileWriter = new FileWriter(path, true);
		if (buffered) {
			output = new BufferedWriter(fileWriter);
		}
	}

	private void tryCreateDirectory(String path) {
		File directory = new File(path).getParentFile();
		if (!directory.exists()) {
			if (!directory.mkdirs()) {
				SelfLog.writeLine("Failed to create directory %s", path);
			}
		}
	}

	public String getFilename() {
		return filename;
	}

	@Override
	public void emit(LogEvent logEvent) {
		if (logEvent == null) {
			throw new IllegalArgumentException("logEvent");
		}

		synchronized (syncRoot) {
			if (output == null) {
				return;
			}

			if (fileSizeLimitBytes != null) {
			}

			try {
				formatter.format(logEvent, output);

				if (!buffered) {
					output.flush();
				}
			} catch (IOException e) {
				SelfLog.writeLine("Write to %s failed", filename);
			}
		}
	}

	@Override
	public void close() throws IOException {
		synchronized (syncRoot) {
			if (output == null) {
				return;
			}

			output.close();
			if (output != fileWriter) {
				fileWriter.close();
			}
			fileWriter = null;
		}
	}

}
