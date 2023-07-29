package com.gd.reservationservices.presentation.performance.response;

import com.gd.reservationservices.application.performance.dto.CreatePerformanceResult;
import com.gd.reservationservices.application.performance.dto.PerformancePlace;

import java.time.LocalDateTime;

public record CreatePerformanceResponse(
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
    public CreatePerformanceResponse(CreatePerformanceResult createPerformanceResult) {
        this(
            createPerformanceResult.place(),
            createPerformanceResult.category(),
            createPerformanceResult.startAt(),
            createPerformanceResult.startReservationAt(),
            createPerformanceResult.endReservationAt(),
            createPerformanceResult.createdAt(),
            createPerformanceResult.updatedAt(),
            createPerformanceResult.title(),
            createPerformanceResult.content(),
            createPerformanceResult.acting(),
            createPerformanceResult.filmRating()
        );
    }
}
