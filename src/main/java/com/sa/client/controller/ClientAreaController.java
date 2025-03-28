package com.sa.client.controller;

import com.sa.client.dto.AreaDto;
import com.sa.client.dto.AreaParamDto;
import com.sa.client.projection.AreaProjection;
import com.sa.client.service.ClientAreaService;
import com.sa.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/area")
public class ClientAreaController {
    @Autowired
    private ClientAreaService clientAreaService;

    @GetMapping("/listArea")
    public ResponseEntity<ApiResponse<List<AreaDto>>> listAreaByParentId(Long superId, Integer language) {
        return new ResponseEntity<>(new ApiResponse<>(true,
                "successful to get area data",
                clientAreaService.listAreaBySuperId(superId, language)
        ), HttpStatus.OK);
    }

    @GetMapping("/getAreaList")
    public ResponseEntity<ApiResponse<List<AreaProjection>>> getAreaList(Long superId, String language) {
        AreaParamDto areaParamDto = new AreaParamDto();
        areaParamDto.setSuperId(superId);
        areaParamDto.setLanguage(language);
        List<AreaProjection> areaListProjection = clientAreaService.getAreaList(areaParamDto);
        return new ResponseEntity<>(new ApiResponse<>(true,
                "successful to get area data",
                clientAreaService.getAreaList(areaParamDto)
        ), HttpStatus.OK);
    }
}
