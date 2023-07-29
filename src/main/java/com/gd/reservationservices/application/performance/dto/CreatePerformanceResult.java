package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Performance;

import java.time.LocalDateTime;

public record CreatePerformanceResult(
    PerformancePlace place,
    LocalDateTime startAt,
    LocalDateTime endAt,
    LocalDateTime startReservationAt,
    LocalDateTime endReservationAt,
    String category,
    String title,
    String content,
    String acting,
    String filmRating,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public CreatePerformanceResult(Performance performance, PerformancePlace place) {
        this(
            place,
            performance.getStartAt(),
            performance.getEndAt(),
            performance.getStartReservationAt(),
            performance.getEndReservationAt(),
            performance.getCategory().toString(),
            performance.getTitle(),
            performance.getContent(),
            performance.getActing(),
            performance.getFilmRating().toString(),
            performance.getCreatedAt(),
            performance.getUpdatedAt()
        );
    }
}
