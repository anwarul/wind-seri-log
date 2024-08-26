package com.wind.LogsToRedis_329.serilogj.parameters;

import com.wind.LogsToRedis_329.serilogj.events.LogEventProperty;
import com.wind.LogsToRedis_329.serilogj.events.MessageTemplate;

import java.util.ArrayList;

public class MessageTemplateProcessorResult {
	public MessageTemplate template;
	public ArrayList<LogEventProperty> properties;
}
