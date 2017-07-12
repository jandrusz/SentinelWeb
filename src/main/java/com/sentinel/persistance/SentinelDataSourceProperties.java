package com.sentinel.persistance;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@ToString
@Data
@Component
@ConfigurationProperties(prefix = SentinelDataSourceProperties.VIEW_PROPERTIES_PREFIX)
@Deprecated
public class SentinelDataSourceProperties {

    public static final String VIEW_PROPERTIES_PREFIX = "datasource";

    public static final String VIEW_DATASOURCE_PROPERTIES_PREFIX = "datasource.main";

    private Map<String, String> main = new HashMap<>();
}