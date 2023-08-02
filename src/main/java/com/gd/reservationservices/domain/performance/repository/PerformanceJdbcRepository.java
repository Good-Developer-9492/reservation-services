package com.gd.reservationservices.domain.performance.repository;

import com.gd.reservationservices.domain.performance.Seat;

import java.util.List;

public interface PerformanceJdbcRepository {
    void saveAll(List<Seat> seats);
}
