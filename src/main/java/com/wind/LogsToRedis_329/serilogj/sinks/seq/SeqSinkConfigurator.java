package com.wind.LogsToRedis_329.serilogj.sinks.seq;

import com.wind.LogsToRedis_329.serilogj.core.ILogEventSink;

import java.util.Map;

public class SeqSinkConfigurator {
	public static ILogEventSink seq(String serverUrl) {
		return seq(serverUrl, null);
	}

	public static ILogEventSink seq(String serverUrl, String apiKey) {
		return seq(serverUrl, apiKey, null);
	}

	public static ILogEventSink seq(String serverUrl, String apiKey, Map<String, String> httpHeaders) {
		return new SeqSink(serverUrl, apiKey, httpHeaders, null, null, null, null);
	}
}
