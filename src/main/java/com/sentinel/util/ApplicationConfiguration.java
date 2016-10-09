package com.sentinel.util;

import com.sentinel.Application;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringApplicationConfiguration(Application.class)
@PropertySource("/application-sentinel.properties")
public class ApplicationConfiguration {
}
