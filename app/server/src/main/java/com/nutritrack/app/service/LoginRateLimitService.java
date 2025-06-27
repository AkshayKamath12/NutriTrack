package com.nutritrack.app.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class LoginRateLimitService {
    private RedisTemplate<String, String> redisTemplate;
    private static final int MAX_RETRY_COUNT = 4;
    private static final Duration ATTEMPT_WINDOW = Duration.ofHours(1);

    public LoginRateLimitService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isLoginAttemptAllowed(String clientIp) {
        String key = "loginAttempts:" + clientIp;
        Long attempts = redisTemplate.opsForValue().increment(key);
        if(attempts != null && attempts == 1) {
            redisTemplate.expire(key, ATTEMPT_WINDOW.toMillis(), TimeUnit.MILLISECONDS);
        }
        return attempts <= MAX_RETRY_COUNT;
    }

    public void resetUponSuccessfulLogin(String clientIp) {
        String key = "loginAttempts:" + clientIp;
        redisTemplate.delete(key);
    }
}
