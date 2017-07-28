package com.sentinel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private static final String SPRING_CONFIG_FILE = "spring.config.name:application-sentinel";

	public static void main(String[] args) {
		new Application().configure(new SpringApplicationBuilder(Application.class)).run();
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.properties(SPRING_CONFIG_FILE);
	}
}
