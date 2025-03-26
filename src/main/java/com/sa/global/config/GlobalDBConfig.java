package com.sa.global.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "globalEntityManagerFactory",
        transactionManagerRef = "globalTransactionManager",
        basePackages = "com.sa.global.repository"
)
@EnableTransactionManagement
public class GlobalDBConfig {

    @Bean(name = "globalDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.global")
    public DataSource globalDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "globalEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean globalEntityManagerFactory(DataSource globalDataSource,
                                                                             EntityManagerFactoryBuilder builder) {
        return builder.dataSource(globalDataSource)
                .packages("com.sa.global.entity")
                .persistenceUnit("global")
                .build();
    }

    @Bean(name = "globalTransactionManager")
    public PlatformTransactionManager globalTransactionManager(@Qualifier("globalEntityManagerFactory") EntityManagerFactory globalEntityManagerFactory) {
        return new JpaTransactionManager(globalEntityManagerFactory);
    }

}
