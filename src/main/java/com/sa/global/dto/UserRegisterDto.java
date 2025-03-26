package com.sa.global.dto;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String region;
    private String email;
    private String password;
    private String phone;
}
