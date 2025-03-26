package com.sa.common.security;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class JwtBlacklistService {
    private final RedisTemplate<String, String> redisTemplate;
    /*
    * After user logout, set the JWT as blacklist, so that the jwt can not be reused.
    * */
    public void blacklist(String jti, long expirySeconds) {
        redisTemplate.opsForValue().set("blacklist:" + jti, "true", expirySeconds, TimeUnit.SECONDS);
    }

    public boolean isBlacklisted(String jti) {
        return redisTemplate.hasKey("blacklist:" + jti);
    }
}
