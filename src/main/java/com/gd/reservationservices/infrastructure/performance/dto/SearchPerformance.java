package com.gd.reservationservices.infrastructure.performance.dto;

import java.time.LocalDateTime;

public record SearchPerformance(
        Long placeId,
        String category,
        String title,
        LocalDateTime searchStartAt,
        LocalDateTime searchEndAt
) {
}
