package com.gd.reservationservices.domain.performance;

public interface LockRepository {
    Boolean  lock(String key);
    Boolean unlock(Long key);
    String get(Long key);
    void set(Long key, String value);
}
