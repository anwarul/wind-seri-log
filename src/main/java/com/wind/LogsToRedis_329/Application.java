package com.wind.LogsToRedis_329;

import com.wind.LogsToRedis_329.serilogj.Log;
import com.wind.LogsToRedis_329.serilogj.LoggerConfiguration;
import com.wind.LogsToRedis_329.serilogj.core.enrichers.LogContextEnricher;
import com.wind.LogsToRedis_329.serilogj.debugging.SelfLog;
import com.wind.LogsToRedis_329.serilogj.events.LogEventLevel;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.*;
import java.util.Collections;

import static com.wind.LogsToRedis_329.serilogj.sinks.coloredconsole.ColoredConsoleSinkConfigurator.coloredConsole;
import static com.wind.LogsToRedis_329.serilogj.sinks.rollingfile.RollingFileSinkConfigurator.rollingFile;
import static com.wind.LogsToRedis_329.serilogj.sinks.seq.SeqSinkConfigurator.seq;

@SpringBootApplication
@EnableAsync
@ServletComponentScan
public class Application {

	public static void main(String[] args) {

		// Set the logging configuration file location
		//System.setProperty("logging.config", "classpath:logback-spring.xml");

		try {
//			File logFile = new File("logs/info.log");
//			if (!logFile.exists()) {
//				if (!logFile.createNewFile()) {
//					throw new IOException("Failed to create log file.");
//				}
//			}
//			BufferedReader reader = new BufferedReader(new FileReader("logs/info.log"));
//			String line = reader.readLine();
//			CloseableHttpClient httpClient = HttpClients.createDefault();
//			while ((line = reader.readLine()) != null) {
//				HttpPost httpPost = new HttpPost("http://localhost:5341/");
//				httpPost.setHeader("Content-Type", "application/json");
//				httpPost.setEntity(new StringEntity(line));
//				CloseableHttpResponse response = httpClient.execute(httpPost);
//			}

			//reader.close();
			//PrintStream output = new PrintStream(logFile);

			// set the log file as the log of SelfLog
			//SelfLog.setOut(output);
		}catch (Exception e){

		}

SelfLog.setOut(System.out);

		Log.setLogger(new LoggerConfiguration()
				.writeTo(rollingFile("test-{Date}.log"), LogEventLevel.Information)
				.writeTo(seq("https://logger.wind.app","oDMFYBjuWgCerQZ3FUED"))
				//.writeTo(seq("http://localhost:5341"))
				.createLogger());

		//Log.information("Hello from {lang}!", "Java");

		//Log.setLogger(configuration.createLogger());
		SpringApplication app = new SpringApplication(Application.class);
		Log.information("In outer operation");
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8084"));
		app.run(args);
	}

}
