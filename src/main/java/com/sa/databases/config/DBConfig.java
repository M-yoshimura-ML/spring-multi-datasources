package com.sa.databases.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "regionalEntityManagerFactory",
        transactionManagerRef = "regionalTransactionManager",
        basePackages = "com.sa.databases.repository"
)
@EnableTransactionManagement
public class DBConfig {
    @Bean(name = "jpDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.jp")
    public DataSource jpDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "usDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.us")
    public DataSource usDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean
    public DataSource dataSource(
            @Qualifier("jpDataSource")DataSource jpDataSource,
            @Qualifier("usDataSource")DataSource usDataSource,
            @Qualifier("globalDataSource")DataSource globalDataSource
    ) {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("jp", jpDataSource);
        targetDataSources.put("us", usDataSource);
        targetDataSources.put("global", globalDataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(jpDataSource);
        return routingDataSource;
    }

    @Primary
    @Bean(name = "regionalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean regionalEntityManagerFactory(DataSource dataSource,
                                                                         EntityManagerFactoryBuilder builder) {
        HashMap<String, Objects> properties = new HashMap<>();
        return builder.dataSource(dataSource)
                .properties(properties)
                .packages("com.sa.databases.entity")
                .build();
    }

    @Primary
    @Bean(name = "regionalTransactionManager")
    public PlatformTransactionManager regionalTransactionManager(@Qualifier("regionalEntityManagerFactory")EntityManagerFactory regionalEntityManagerFactory) {
        return new JpaTransactionManager(regionalEntityManagerFactory);
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
