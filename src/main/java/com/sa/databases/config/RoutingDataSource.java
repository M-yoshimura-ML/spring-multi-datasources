package com.sa.databases.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("determineCurrentLookupKey: " + CONTEXT.get());

        return CONTEXT.get();
    }

    public static void setDataSource(String region) {
        CONTEXT.set(region);
    }

    public static void clearDataSource() {
        CONTEXT.remove();
    }

}
