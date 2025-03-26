package com.sa.common.config;

import com.sa.databases.config.RoutingDataSource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class RegionalInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String region = request.getParameter("region");
        System.out.println("region: " + region);

        if (region != null && !isValidRegion(region)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid region specified: " + region);
            return false;
        } else if (region != null) {
            RoutingDataSource.setDataSource(region);
        } else {
            RoutingDataSource.setDataSource("jp");
        }

        return true;
    }

    private boolean isValidRegion(String region) {
        return "jp".equals(region) || "us".equals(region) || "hk".equals(region) || "global".equals(region);
    }
}
