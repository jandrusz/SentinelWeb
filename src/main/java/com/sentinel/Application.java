package com.sentinel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static final String SPRING_CONFIG_NAME = "spring.config.name:application-sentinel";

    public static void main(String[] args) {
        new Application()
                .configure(new SpringApplicationBuilder(Application.class).properties(SPRING_CONFIG_NAME))
                .run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class).properties(SPRING_CONFIG_NAME);
    }
}
