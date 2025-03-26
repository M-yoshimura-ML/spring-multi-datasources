package com.sa.platform.service;

import com.sa.databases.config.RoutingDataSource;
import com.sa.databases.entity.Test;
import com.sa.databases.service.TestService;
import com.sa.global.entity.GlobalTest;
import com.sa.global.repository.GlobalTestRepository;
import com.sa.platform.dto.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlatformTestService {
    @Autowired
    private TestService testService;
    @Autowired
    private GlobalTestRepository globalTestRepository;

    @Transactional(transactionManager = "globalTransactionManager")
    public GlobalTest getGlobalTestData() {
        RoutingDataSource.setDataSource("global");
        return globalTestRepository.findById(1L).orElse(null);
    }

    public TestDto getRegionalAndGlobalTestData(String region) {
        Test testData = testService.getTest();

        GlobalTest globalTestData = getGlobalTestData();

        TestDto testDto = new TestDto();
        testDto.setRegion(region);
        testDto.setTestColumn(testData.getTestColumn());
        testDto.setTestGlobalColumn(globalTestData.getTestColumn());

        return testDto;
    }
}
