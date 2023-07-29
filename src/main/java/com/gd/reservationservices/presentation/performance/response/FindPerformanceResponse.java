package com.gd.reservationservices.presentation.performance.response;

import com.gd.reservationservices.application.performance.dto.PerformancePlace;
import com.gd.reservationservices.application.performance.dto.SearchPerformanceResult;

import java.time.LocalDateTime;

public record FindPerformanceResponse(
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
    public FindPerformanceResponse(SearchPerformanceResult searchPerformanceResult) {
        this(
            searchPerformanceResult.id(),
            searchPerformanceResult.place(),
            searchPerformanceResult.category(),
            searchPerformanceResult.startAt(),
            searchPerformanceResult.startReservationAt(),
            searchPerformanceResult.endReservationAt(),
            searchPerformanceResult.createdAt(),
            searchPerformanceResult.updatedAt(),
            searchPerformanceResult.title(),
            searchPerformanceResult.content(),
            searchPerformanceResult.acting(),
            searchPerformanceResult.filmRating()
        );
    }
}
