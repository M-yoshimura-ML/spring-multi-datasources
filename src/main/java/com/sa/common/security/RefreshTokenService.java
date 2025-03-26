package com.sa.common.security;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RefreshTokenService {
    private final RedisTemplate<String, String> redisTemplate;

    public void storeRefreshToken(String username, String refreshToken, long expirationMs) {
        redisTemplate.opsForValue().set("refresh:" + username, refreshToken, expirationMs, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(String username) {
        return redisTemplate.opsForValue().get("refresh:" + username);
    }

    public void revokeRefreshToken(String username) {
        redisTemplate.delete("refresh:" + username);
    }
}
