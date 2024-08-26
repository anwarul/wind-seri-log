package com.wind.LogsToRedis_329.serilogj.sinks.coloredconsole;

import com.wind.LogsToRedis_329.serilogj.core.ILogEventSink;

import java.util.Locale;

public class ColoredConsoleSinkConfigurator {
	private static String DefaultConsoleOutputTemplate = "{Timestamp:yyyy-MM-dd HH:mm:ss} [{Level}] {Message}{NewLine}{Exception}";

	public static ILogEventSink coloredConsole() {
		return coloredConsole(DefaultConsoleOutputTemplate, null);
	}

	public static ILogEventSink coloredConsole(String outputTemplate) {
		return coloredConsole(outputTemplate, null);
	}

	public static ILogEventSink coloredConsole(Locale locale) {
		return coloredConsole(DefaultConsoleOutputTemplate, locale);
	}

	public static ILogEventSink coloredConsole(String outputTemplate, Locale locale) {
		return new ColoredConsoleSink(outputTemplate, locale);
	}
}
