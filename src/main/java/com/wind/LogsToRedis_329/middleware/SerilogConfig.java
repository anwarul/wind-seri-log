package com.wind.LogsToRedis_329.middleware;

import com.wind.LogsToRedis_329.serilogj.Log;
import com.wind.LogsToRedis_329.serilogj.LoggerConfiguration;
import com.wind.LogsToRedis_329.serilogj.core.enrichers.LogContextEnricher;
import com.wind.LogsToRedis_329.serilogj.events.LogEventLevel;
import com.wind.LogsToRedis_329.serilogj.sinks.rollingfile.FileSink;
import com.wind.LogsToRedis_329.serilogj.sinks.seq.SeqSink;
import com.wind.LogsToRedis_329.serilogj.events.LogEventProperty;
import com.wind.LogsToRedis_329.serilogj.core.LoggingLevelSwitch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.wind.LogsToRedis_329.serilogj.sinks.coloredconsole.ColoredConsoleSinkConfigurator.coloredConsole;
import static com.wind.LogsToRedis_329.serilogj.sinks.rollingfile.RollingFileSinkConfigurator.rollingFile;
import static com.wind.LogsToRedis_329.serilogj.sinks.seq.SeqSinkConfigurator.seq;

//@Configuration
public class SerilogConfig {

   // @Bean
    public void configureSerilog() {
        LoggerConfiguration configuration = new LoggerConfiguration()
                .setMinimumLevel(LogEventLevel.Error)
                .with(new LogContextEnricher())
                .writeTo(coloredConsole("[{Timestamp} {Level}] {Message} ({Operation}){NewLine}{Exception}"))
                .writeTo(rollingFile("logs/info.log"))
                .writeTo(seq("http://localhost:5341/"));
                //.outputTemplate("{\"@t\":\"{Timestamp:O}\",\"@mt\":\"HTTP {RequestMethod} {RequestPath} responded {StatusCode} in {Elapsed:0.0000} ms\",\"@m\":\"{Message}\",\"@i\":\"{EventId}\",\"@r\":[\"{Elapsed}\"],\"@tr\":\"{TraceId}\",\"@sp\":\"{SpanId}\",\"ConnectionId\":\"{ConnectionId}\",\"CorrelationId\":\"{CorrelationId}\",\"Elapsed\":{Elapsed},\"RequestId\":\"{RequestId}\",\"RequestMethod\":\"{RequestMethod}\",\"RequestPath\":\"{RequestPath}\",\"SourceContext\":\"{SourceContext}\",\"StatusCode\":{StatusCode}}");

       Log.setLogger(configuration.createLogger());
    }
}