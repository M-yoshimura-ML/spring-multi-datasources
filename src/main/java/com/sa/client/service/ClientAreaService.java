package com.sa.client.service;

import com.sa.client.dto.AreaDto;
import com.sa.client.dto.AreaParamDto;
import com.sa.client.projection.AreaProjection;
import com.sa.databases.config.RoutingDataSource;
import com.sa.global.entity.Area;
import com.sa.global.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientAreaService {
    @Autowired
    private AreaRepository areaRepository;

    public List<AreaDto> listAreaBySuperId(Long superId, Integer language) {
        if (superId == null) {
            superId = 0L;
        }

        if (language == null) {
            language = 2; // default JP
        }

        RoutingDataSource.setDataSource("global");
        List<Area> areas = areaRepository.findAllBySuperId(superId);
        List<AreaDto> rspList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(areas)) {
            for (Area area : areas) {
                AreaDto rsp = new AreaDto(area, language);
                rspList.add(rsp);
            }
        }

        return rspList;
    }

    public List<AreaProjection> getAreaList(AreaParamDto areaParamDto) {
        RoutingDataSource.setDataSource("global");
        return areaRepository.getAreaList(areaParamDto);
    }
}
