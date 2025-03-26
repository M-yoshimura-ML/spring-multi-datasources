package com.sa.common.liquibase;

import liquibase.integration.spring.SpringLiquibase;

import javax.sql.DataSource;
import java.util.Map;

public class MultiTenantLiquibase {
    public static void applyLiquibase(Map<String, DataSource> dataSourceMap) {
        int countJp = 0;
        int countUs = 0;

        for (Map.Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
            SpringLiquibase liquibase = new SpringLiquibase();
            liquibase.setDataSource(entry.getValue());

            System.out.println("Processing datasource: " + entry.getKey());
            try {
                if (entry.getKey().equals("jpDataSource")) {
                    liquibase.setChangeLog("classpath:/liquibase/regional/db/changelog/db.changelog-master-jp.xml");
                    countJp++;
                } else if (entry.getKey().equals("usDataSource")) {
                    liquibase.setChangeLog("classpath:/liquibase/regional/db/changelog/db.changelog-master-us.xml");
                    countUs++;
                } else if (entry.getKey().equals("globalDataSource")) {
                    liquibase.setChangeLog("classpath:/liquibase/global/db/changelog/db.changelog-master-global.xml");
                } else {
                    // Handle unexpected data sources (either log or throw an exception)
                    System.err.println("Unknown datasource: " + entry.getKey() + ". Skipping processing.");
                    continue;
                }

                // Validate that changelog is set before running Liquibase
                if (liquibase.getChangeLog() == null || liquibase.getChangeLog().isEmpty()) {
                    throw new IllegalStateException("Changelog is not set for datasource: " + entry.getKey());
                }

                liquibase.afterPropertiesSet(); // Process Liquibase changeset
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("countJp : " + countJp);
        System.out.println("countUs : " + countUs);
    }
}
