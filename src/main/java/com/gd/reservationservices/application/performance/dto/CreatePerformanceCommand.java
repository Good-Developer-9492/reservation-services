package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Place;

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
        String filmRating,
        int price
        ) {
    public Performance toEntity(Place place) {
        return new Performance(
                place,
                Performance.Category.valueOf(this.category),
                this.startAt,
                this.endAt,
                this.startReservationAt,
                this.endReservationAt,
                this.title,
                this.content,
                this.acting,
                Performance.FilmRating.valueOf(this.filmRating),
                this.price
        );
    }
}