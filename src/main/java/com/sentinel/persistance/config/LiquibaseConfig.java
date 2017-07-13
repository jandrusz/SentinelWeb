package com.sentinel.persistance.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

@Configuration
public class LiquibaseConfig {

    @Inject
    private DataSource dataSource;

    @Inject
    private Environment environment;

    @NotNull
    @Value("${liquibase.mainChangelogFileName}")
    private String mainChangelogFileName;

    @Bean(name = "liquibase")
    public SpringLiquibase liquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog("classpath:" + mainChangelogFileName);
        springLiquibase.setDataSource(dataSource);
        return springLiquibase;
    }
}
