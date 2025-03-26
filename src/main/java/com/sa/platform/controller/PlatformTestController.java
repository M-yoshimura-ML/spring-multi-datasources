package com.sa.platform.controller;

import com.sa.platform.service.PlatformTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platform")
public class PlatformTestController {
    @Autowired
    private PlatformTestService platformTestService;

    @GetMapping("/getGlobal")
    public Object getGlobalTestData() {
        return platformTestService.getGlobalTestData();
    }

    @GetMapping("/getRegionAndGlobal")
    public Object getRegionAndGlobalTestData(String region) {
        return platformTestService.getRegionalAndGlobalTestData(region);
    }
}
