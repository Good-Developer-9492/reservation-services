package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Performance;

import java.time.LocalDateTime;

public record SearchPerformanceResult(
    Long id,
    PerformancePlace place,
    String category,
    LocalDateTime startAt,
    LocalDateTime startReservationAt,
    LocalDateTime endReservationAt,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String title,
    String content,
    String acting,
    String filmRating
) {
    public SearchPerformanceResult(Performance performance, PerformancePlace place) {
        this(
            performance.getId(),
            place,
            performance.getCategory().toString(),
            performance.getStartAt(),
            performance.getStartReservationAt(),
            performance.getEndReservationAt(),
            performance.getCreatedAt(),
            performance.getUpdatedAt(),
            performance.getTitle(),
            performance.getContent(),
            performance.getActing(),
            performance.getFilmRating().toString()
        );
    }
}
