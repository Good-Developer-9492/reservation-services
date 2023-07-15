package com.gd.reservationservices.presentation.performance.response;

import com.gd.reservationservices.application.performance.dto.SearchPerformance;
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
    public FindPerformanceResponse(SearchPerformance searchPerformance) {
        this(
            searchPerformance.id(),
            searchPerformance.place(),
            searchPerformance.category(),
            searchPerformance.startAt(),
            searchPerformance.startReservationAt(),
            searchPerformance.endReservationAt(),
            searchPerformance.createdAt(),
            searchPerformance.updatedAt(),
            searchPerformance.title(),
            searchPerformance.content(),
            searchPerformance.acting(),
            searchPerformance.filmRating()
        );
    }
}
