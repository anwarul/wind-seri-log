package com.wind.LogsToRedis_329.serilogj.core.enrichers;

import com.wind.LogsToRedis_329.serilogj.context.LogContext;
import com.wind.LogsToRedis_329.serilogj.core.ILogEventEnricher;
import com.wind.LogsToRedis_329.serilogj.core.ILogEventPropertyFactory;
import com.wind.LogsToRedis_329.serilogj.events.LogEvent;

public class LogContextEnricher implements ILogEventEnricher {
	@Override
	public void enrich(LogEvent logEvent, ILogEventPropertyFactory propertyFactory) {
		for(ILogEventEnricher enricher : LogContext.getEnrichers()) {
			enricher.enrich(logEvent, propertyFactory);
		}
	}
}
