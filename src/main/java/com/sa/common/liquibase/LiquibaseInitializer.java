package com.sa.common.liquibase;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

@Component
public class LiquibaseInitializer {
    private final Map<String, DataSource> dataSourceMap;

    public LiquibaseInitializer(Map<String, DataSource> dataSourceMap) {
        this.dataSourceMap = dataSourceMap;
    }

    @PostConstruct
    public void init() {
        MultiTenantLiquibase.applyLiquibase(dataSourceMap);
    }
}
