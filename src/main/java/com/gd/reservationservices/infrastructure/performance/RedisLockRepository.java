package com.gd.reservationservices.infrastructure.performance;

import com.gd.reservationservices.domain.performance.LockRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisLockRepository implements LockRepository {
    private final RedisTemplate<String, String> redisTemplate;

    public RedisLockRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Boolean lock(String key) {
        return redisTemplate.opsForValue()
                .setIfAbsent(key, "Lock");
    }

    @Override
    public Boolean unlock(String key) {
        return redisTemplate.delete(String.valueOf(key));
    }

    @Override
    public String get(Long key) {
        return redisTemplate.opsForValue().get(String.valueOf(key));
    }

    @Override
    public void set(Long key, String value) {
        redisTemplate.opsForValue().set(String.valueOf(key), value);
    }
}
