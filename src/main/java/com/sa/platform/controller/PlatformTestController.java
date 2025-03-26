package com.sa.platform.controller;

import com.sa.common.service.RedisService;
import com.sa.platform.service.PlatformTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/platform/test")
public class PlatformTestController {
    @Autowired
    private PlatformTestService platformTestService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/getGlobal")
    public Object getGlobalTestData() {
        return platformTestService.getGlobalTestData();
    }

    @GetMapping("/getRegionAndGlobal")
    public Object getRegionAndGlobalTestData(String region) {
        return platformTestService.getRegionalAndGlobalTestData(region);
    }

    @PostMapping("/setKey")
    public String setValue(String key, String value) {
        redisService.setValue(key, value, 300);
        return "key: " + key + " saved";
    }

    @GetMapping("/getValue")
    public Object getValue(String key) {
        return redisService.getValue(key);
    }

    @DeleteMapping("/deleteValue")
    public String deleteValue(String key) {
        redisService.deleteValue(key);
        return "key: " + key + " deleted";
    }
}
