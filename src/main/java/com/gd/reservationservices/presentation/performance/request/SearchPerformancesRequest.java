package com.gd.reservationservices.presentation.performance.request;

import com.gd.reservationservices.application.performance.dto.SearchPerformancesValue;

import java.time.LocalDateTime;

public record SearchPerformancesRequest(
    Long placeId,
    String category,
    String title,
    LocalDateTime searchStartAt,
    LocalDateTime searchEndAt
) {
    public SearchPerformancesValue toValue() {
        return new SearchPerformancesValue(
            this.placeId,
            this.category,
            this.title,
            this.searchStartAt,
            this.searchEndAt
        );
    }
}
