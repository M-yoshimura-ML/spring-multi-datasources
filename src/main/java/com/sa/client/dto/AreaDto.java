package com.sa.client.dto;

import com.sa.global.entity.Area;
import lombok.Data;

@Data
public class AreaDto {
    private Long id;
    private String areaName;
    private String code;

    public AreaDto(Area area, Integer area_language) {
        // belong area：0中文简体(CH)、1中文繁体(HK)、2JP、3EN(usa)
        this.id =area.getId();
        this.code = area.getCode();
        if(area_language.equals(0)) {
            areaName = area.getNameCh();
        }else if(area_language.equals(1)) {
            areaName = area.getNameHk();
        }else if(area_language.equals(2)) {
            areaName = area.getNameJp();
        }else if(area_language.equals(3)) {
            areaName = area.getNameUs();
        }
    }
}
