package com.gd.reservationservices.domain.performance.repository;

import java.util.concurrent.TimeUnit;

public interface LockRepository {
    Boolean lock(String key, String value, Long leaseTime, TimeUnit timeUnit);
    void unlock(String key);
    String get(String key);
    void set(Long key, String value);
}
