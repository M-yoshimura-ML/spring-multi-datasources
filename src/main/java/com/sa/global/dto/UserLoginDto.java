package com.sa.global.dto;

import lombok.Data;

@Data
public class UserLoginDto {
    private String region;
    private String phone;
    private String email;
    private String password;
}
