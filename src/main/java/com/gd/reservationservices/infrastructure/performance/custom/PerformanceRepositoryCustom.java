package com.gd.reservationservices.infrastructure.performance.custom;

import com.gd.reservationservices.domain.performance.Performance;

import java.util.Optional;

public interface PerformanceRepositoryCustom {
    Optional<Performance> findPerformanceAndPlace(Long id);
}
