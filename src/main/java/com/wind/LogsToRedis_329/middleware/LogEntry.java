package com.wind.LogsToRedis_329.middleware;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class LogEntry {

    @JsonProperty("@m")
    private String message;

    @JsonProperty("@i")
    private String id;

    @JsonProperty("@r")
    private String[] responseTime;

    @JsonProperty("@tr")
    private String traceId;

    @JsonProperty("@sp")
    private String spanId;

    private String connectionId;

    private String correlationId;

    private double elapsed;

    private String requestId;

    private String requestMethod;

    private String requestPath;

    private Map<String, Object> requestBody;

    private String sourceContext;

    private int statusCode;

    // Getters and setters
    // ...
}