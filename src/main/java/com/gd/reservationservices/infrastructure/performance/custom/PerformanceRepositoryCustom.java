package com.gd.reservationservices.infrastructure.performance.custom;

import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.infrastructure.performance.dto.SearchPerformance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PerformanceRepositoryCustom {
    boolean exists(Long placeId, LocalDateTime startAt, LocalDateTime endAt);
    Optional<Performance> findPerformanceAndPlace(Long id);
    Page<Performance> findAllWithPlace(SearchPerformance request, Pageable pageable);
}
