package com.sa.global.repository;

import com.sa.global.entity.GlobalTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(transactionManager = "globalTransactionManager")
public interface GlobalTestRepository extends JpaRepository<GlobalTest, Long> {
}
