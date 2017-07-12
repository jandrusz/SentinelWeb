//package com.sentinel.persistance.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.inject.Inject;
//import javax.sql.DataSource;
//import java.util.Map;
//import java.util.Properties;
//
//@Configuration
//@EnableJpaRepositories(basePackages = "com.sentinel.persistance")
//@EnableTransactionManagement
//public class DatabaseTestConfiguration {
//
//    @Bean
//    DataSource dataSource() {
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        return builder.setType(EmbeddedDatabaseType.HSQL).build();
//    }
//
//    @Bean
//    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setDataSource(dataSource());
//        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        factory.setPackagesToScan("com.sentinel");
//        return factory;
//    }
//
//    @Bean
//    PlatformTransactionManager transactionManager() {
//        return new JpaTransactionManager(entityManagerFactory().getObject());
//    }
//
////        private HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
////        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
////        vendorAdapter.setDatabase(Database.ORACLE);
////        vendorAdapter.setGenerateDdl(true);
////        vendorAdapter.setShowSql(true);
////        return vendorAdapter;
////    }
//
//
//}
