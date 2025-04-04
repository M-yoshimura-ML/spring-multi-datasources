package com.sa.platform.controller;

import com.sa.common.security.JwtBlacklistService;
import com.sa.common.security.JwtService;
import com.sa.common.security.RefreshTokenService;
import com.sa.global.dto.UserLoginDto;
import com.sa.global.dto.UserRegisterDto;
import com.sa.global.entity.User;
import com.sa.global.service.GlobalUserService;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/platform/auth")
public class PlatformAuthController {
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final JwtBlacklistService blacklistService;
    private final GlobalUserService globalUserService;

    @Value("${jwt.refreshTokenExpiration}")
    private long refreshTokenExpiration;

    public PlatformAuthController(JwtService jwtService, RefreshTokenService refreshTokenService,
                                  JwtBlacklistService blacklistService, GlobalUserService globalUserService) {
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.blacklistService = blacklistService;
        this.globalUserService = globalUserService;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserLoginDto userDto) {
        User user = globalUserService.findUserByEmail(userDto);
        String accessToken = jwtService.generateAccessToken(user.getEmail());
        String refreshToken = UUID.randomUUID().toString();
        refreshTokenService.storeRefreshToken(user.getEmail(), refreshToken, refreshTokenExpiration);

        return Map.of("accessToken", accessToken, "refreshToken", refreshToken);
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestParam String username, @RequestParam String refreshToken) {
        String stored = refreshTokenService.getRefreshToken(username);
        if (stored != null && stored.equals(refreshToken)) {
            String accessToken = jwtService.generateAccessToken(username);
            return Map.of("accessToken", accessToken);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }
    }

    @PostMapping("/register")
    public String register(@RequestBody UserRegisterDto userDto) {
        globalUserService.registerUser(userDto);
        return "Successfully register";
    }

    @PostMapping("/logout")
    public void logout(@RequestParam String accessToken) {
        Claims claims = jwtService.parseToken(accessToken);
        long expSeconds = (claims.getExpiration().getTime() - System.currentTimeMillis()) / 1000;
        blacklistService.blacklist(claims.getId(), expSeconds);
        refreshTokenService.revokeRefreshToken(claims.getSubject());
    }
}
