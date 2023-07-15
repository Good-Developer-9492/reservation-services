package com.gd.reservationservices.presentation.performance.response;

import com.gd.reservationservices.application.performance.dto.CreatePerformanceResult;
import com.gd.reservationservices.application.performance.dto.PerformancePlace;
import com.gd.reservationservices.infrastructure.performance.value.Category;
import com.gd.reservationservices.infrastructure.performance.value.FilmRating;

import java.time.LocalDateTime;

public record CreatePerformanceResponse(
    PerformancePlace place,
    Category category,
    LocalDateTime startAt,
    LocalDateTime startReservationAt,
    LocalDateTime endReservationAt,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String title,
    String content,
    String acting,
    FilmRating filmRating
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
