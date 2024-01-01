package com.gd.reservationservices.domain.performance.repository;

public interface LockRepository {
    Boolean lock(String key, String value, Long leaseTime);
    void unlock(String key);
    String get(String key);
    void set(Long key, String value);
}
