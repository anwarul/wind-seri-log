package com.wind.LogsToRedis_329.middleware;


import com.wind.LogsToRedis_329.serilogj.Log;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.UUID;


public class CorrelationIdFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CorrelationIdFilter.class);


    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        long startTime = System.currentTimeMillis();
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);

        LogEntry logEntry=new LogEntry();
        // Generate and set unique identifiers
        String requestId = UUID.randomUUID().toString();
        String traceId = UUID.randomUUID().toString();
        String spanId = UUID.randomUUID().toString();
        String scw="";
        String username="";
        String body="";
        String accessToken="";

        ContentCachingRequestWrapper cachedRequest = new ContentCachingRequestWrapper(request);


        ContentCachingRequestWrapper _cachedRequest = new ContentCachingRequestWrapper(request);
        String payload = IOUtils.toString(_cachedRequest.getRequest().getInputStream(), StandardCharsets.UTF_8);

        body = IOUtils.toString(cachedBodyHttpServletRequest.getInputStream(), StandardCharsets.UTF_8);


        accessToken=request.getHeader("Authorization");
        logEntry.setRequestId(requestId);
        logEntry.setTraceId(traceId);
        logEntry.setSpanId(spanId);
        logEntry.setCorrelationId(request.getHeader("CorrelationId"));
        logEntry.setConnectionId(request.getHeader("Connection"));
        MDC.put("requestId", requestId);
        MDC.put("traceId", traceId);
        MDC.put("spanId", spanId);
        MDC.put("connectionId", request.getHeader("Connection"));
        MDC.put("correlationId", request.getHeader("CorrelationId"));
        MDC.put("userId", request.getHeader("x-user-id"));
        MDC.put("scw", request.getHeader("x-scw"));
        MDC.put("username", request.getHeader("x-username"));
        MDC.put("requestMethod", request.getMethod());
        MDC.put("requestPath", request.getRequestURI());

        try {
            filterChain.doFilter(cachedBodyHttpServletRequest, response);
        } finally {
            long elapsedTime = System.currentTimeMillis() - startTime;
            MDC.put("elapsed", String.valueOf(elapsedTime));
            MDC.put("statusCode", String.valueOf(response.getStatus()));

            // Create log message
            String logMessage = String.format("HTTP %s %s responded %d in %f ms",
                    request.getMethod(), request.getRequestURI(), response.getStatus(), elapsedTime / 1000.0);

            MDC.put("message", logMessage);


            MDC.put("requestBody", body); // Capture request body
            // Log the message
            logger.info(logMessage);
            Log.information("Info: {UserId} {Username} {SCW}  {Wallet} Message: {message} AccessToken:{AccessToken} {CorrelationId} {TraceId} {RequestId}  {RequestMethod} {RequestPath} {RequestBody} {NetworkStatus}",

                    request.getHeader("x-user-id"),
                    request.getHeader("x-username"),
                    request.getHeader("x-scw"),
                    request.getHeader("x-wallet"),
                    logMessage,
                    accessToken,
                    logEntry.getCorrelationId(),
                    logEntry.getTraceId(),
                    logEntry.getRequestId(),
                    request.getMethod(),
                    request.getRequestURI(),
                    body,
                    response.getStatus()
            );

            /*Log.information("Info: {CorrelationId} {TraceId} {RequestId} {SCW} {Wallet} {UserId} {Username} {RequestMethod} {RequestPath} {RequestBody} {NetworkStatus}",
                    logEntry.getCorrelationId(),
                    logEntry.getTraceId(),
                    logEntry.getRequestId(),
                    request.getHeader("x-scw"),
                    request.getHeader("x-wallet"),
                    request.getHeader("x-user-id"),
                    request.getHeader("x-username"),
                    request.getMethod(),
                    request.getRequestURI(),
                    body,
                    response.getStatus()
                    );
*/
            // Clear MDC after logging
            MDC.clear();

            responseWrapper.copyBodyToResponse();
        }
    }
    private static String extractPostRequestBody(jakarta.servlet.http.HttpServletRequest  request) throws Exception{
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String body = null;
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = null;

            try {
                InputStream inputStream = request.getInputStream();
                if (inputStream != null) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    char[] charBuffer = new char[128];
                    int bytesRead = -1;
                    while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                        stringBuilder.append(charBuffer, 0, bytesRead);
                    }
                } else {
                    stringBuilder.append("");
                }

            } catch (IOException ex) {
                throw ex;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
            }

            body = stringBuilder.toString();
            return body;
        }
        return "";
    }
}