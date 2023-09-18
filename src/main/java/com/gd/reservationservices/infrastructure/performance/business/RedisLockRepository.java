package com.gd.reservationservices.infrastructure.performance.business;

import com.gd.reservationservices.domain.performance.repository.LockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@RequiredArgsConstructor
@Repository
public class RedisLockRepository implements LockRepository {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean lock(String key, String value, Long leaseTime) {
        return redisTemplate.opsForValue()
                .setIfAbsent(key, value, Duration.ofMillis(leaseTime));
    }

    @Override
    public void unlock(String key) {
        redisTemplate.delete(String.valueOf(key));
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(Long key, String value) {
        redisTemplate.opsForValue().set(String.valueOf(key), value);
    }
}
