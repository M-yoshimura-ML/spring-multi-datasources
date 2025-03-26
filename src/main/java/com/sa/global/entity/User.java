package com.sa.global.entity;

import com.sa.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "user")
public class User extends BaseEntity {
    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;
}
