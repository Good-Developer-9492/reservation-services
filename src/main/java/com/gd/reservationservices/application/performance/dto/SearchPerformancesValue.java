package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.infrastructure.performance.dto.SearchPerformance;

import java.time.LocalDateTime;

public record SearchPerformancesValue(
    Long placeId,
    String category,
    String title,
    LocalDateTime searchStartAt,
    LocalDateTime searchEndAt
) {
    public SearchPerformance toValue() {
        return new SearchPerformance(
            this.placeId,
            this.category,
            this.title,
            this.searchStartAt,
            this.searchEndAt
        );
    }
}
