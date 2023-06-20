package com.gd.reservationservices.application.performance.dto;

import java.time.LocalDateTime;

public record CreatePerformanceCommand(
        Long placeId,
        String category,
        LocalDateTime startAt,
        LocalDateTime endAt,
        LocalDateTime startReservationAt,
        LocalDateTime endReservationAt,
        String title,
        String content,
        String acting,
        String filmRating
) {
}
