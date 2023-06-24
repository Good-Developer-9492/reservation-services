package com.gd.reservationservices.presentation.performance.response;

import com.gd.reservationservices.application.performance.dto.FindPerformance;
import com.gd.reservationservices.application.performance.dto.PerformancePlace;
import com.gd.reservationservices.infrastructure.performance.value.Category;
import com.gd.reservationservices.infrastructure.performance.value.FilmRating;

import java.time.LocalDateTime;

public record FindPerformanceResponse(
    Long id,
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
    public FindPerformanceResponse(FindPerformance findPerformance) {
        this(
            findPerformance.id(),
            findPerformance.place(),
            findPerformance.category(),
            findPerformance.startAt(),
            findPerformance.startReservationAt(),
            findPerformance.endReservationAt(),
            findPerformance.createdAt(),
            findPerformance.updatedAt(),
            findPerformance.title(),
            findPerformance.content(),
            findPerformance.acting(),
            findPerformance.filmRating()
        );
    }
}
