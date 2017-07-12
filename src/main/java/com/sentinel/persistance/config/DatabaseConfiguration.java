package com.sentinel.persistance.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.sentinel.persistance")
@EnableTransactionManagement
public class DatabaseConfiguration {

    public static final String DATASOURCE_PROPERTIES_PREFIX = "datasource.main";

    @Bean
    @ConfigurationProperties(prefix = DATASOURCE_PROPERTIES_PREFIX)
    DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("oracle.jdbc.driver.OracleDriver")
                .password("sentinel")
                .username("sentinel")
                .url("jdbc:oracle:thin:@//192.168.99.100:49161/XE")
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = DATASOURCE_PROPERTIES_PREFIX)
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        factory.setPackagesToScan("com.sentinel");
        return factory;
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

     private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.ORACLE);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

}
