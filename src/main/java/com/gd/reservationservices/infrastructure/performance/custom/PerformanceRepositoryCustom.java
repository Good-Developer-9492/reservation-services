package com.gd.reservationservices.infrastructure.performance.custom;

import java.time.LocalDateTime;

public interface PerformanceRepositoryCustom {
    boolean exists(Long placeId, LocalDateTime startAt, LocalDateTime endAt);
}
