package com.wind.LogsToRedis_329.middleware;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisAppender extends AppenderBase<ILoggingEvent> {

    private RedisTemplate<String, String> redisTemplate;
    private StringRedisTemplate stringRedisTemplate;
    private String redisChannel;

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (!isStarted()) {
            return;
        }
        try {
            /*String logMessage = eventObject.toString();
            stringRedisTemplate.convertAndSend(redisChannel, logMessage);*/
            String logMessage = formatLogMessage(eventObject);
            stringRedisTemplate.convertAndSend(redisChannel, logMessage);
        } catch (Exception ex) {
            addError("Failed to push log to Redis", ex);
        }
    }
    private String formatLogMessage(ILoggingEvent eventObject) {
        // Formatting log message based on the provided format
        return String.format(
                "{ \"@t\": \"%s\", \"@mt\": \"%s\", \"@m\": \"%s\", \"@i\": \"%s\", \"RequestMethod\": \"%s\", \"RequestPath\": \"%s\", \"StatusCode\": %d }",
                eventObject.getTimeStamp(),
                eventObject.getFormattedMessage(),
                eventObject.getMessage(),
                eventObject.getThreadName(),
                eventObject.getLoggerName(),  // As an example; modify as needed for actual request data
                eventObject.getLoggerName(),  // As an example; modify as needed for actual request path
                eventObject.getLevel().toInt() // Use as an example for status code
        );
    }
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void setRedisChannel(String redisChannel) {
        this.redisChannel = redisChannel;
    }
}