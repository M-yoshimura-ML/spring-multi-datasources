package com.sa.global.repository;

import com.sa.client.dto.AreaParamDto;
import com.sa.client.projection.AreaProjection;
import com.sa.global.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(transactionManager = "globalTransactionManager")
public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findAllBySuperId(Long superId);

    @Query(value = "CALL get_area_by_super_id(:#{#areaParamDto.superId}, :#{#areaParamDto.language})",
            nativeQuery = true)
    List<AreaProjection> getAreaList(AreaParamDto areaParamDto);
}
