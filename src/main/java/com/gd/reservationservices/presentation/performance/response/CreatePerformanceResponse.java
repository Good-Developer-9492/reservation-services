package com.gd.reservationservices.presentation.performance.response;

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
}
