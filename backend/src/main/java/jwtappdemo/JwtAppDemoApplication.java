package jwtappdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(exclude = {TransactionAutoConfiguration.class, DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class})
public class JwtAppDemoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		if (System.getProperty("server.servlet.contextPath") == null) {
			System.setProperty("server.servlet.contextPath", "/");
		}
		SpringApplication springApplication = new SpringApplication(JwtAppDemoApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());//для создания application.pid файла
		springApplication.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JwtAppDemoApplication.class);
	}

}
