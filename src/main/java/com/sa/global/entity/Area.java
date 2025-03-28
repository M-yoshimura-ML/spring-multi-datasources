package com.sa.global.entity;

import com.sa.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(
        name = "area",
        indexes = {
                @Index(name = "idx_area_super_id", columnList = "super_id")
        }
)
public class Area extends BaseEntity {
    @Column(name = "code")
    private String code;

    @Column(name = "name_ch")
    private String nameCh;

    @Column(name = "name_hk")
    private String nameHk;

    @Column(name = "name_jp")
    private String nameJp;

    @Column(name = "name_us")
    private String nameUs;

    @Column(name = "super_id", nullable = false)
    private Long superId;
}
