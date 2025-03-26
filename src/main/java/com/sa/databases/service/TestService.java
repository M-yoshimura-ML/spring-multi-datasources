package com.sa.databases.service;

import com.sa.databases.entity.Test;
import com.sa.databases.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    @Transactional(transactionManager = "regionalTransactionManager")
    public Test getTest() {
        return testRepository.findById(1L).orElse(null);
    }
}
