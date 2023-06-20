package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.infrastructure.performance.value.Category;
import com.gd.reservationservices.infrastructure.performance.value.FilmRating;

import java.time.LocalDateTime;

public record CreatePerformance(
    PerformancePlace place,
    LocalDateTime startAt,
    LocalDateTime endAt,
    LocalDateTime startReservationAt,
    LocalDateTime endReservationAt,
    Category category,
    String title,
    String content,
    String acting,
    FilmRating filmRating,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public CreatePerformance(Performance performance, PerformancePlace place) {
        this(
            place,
            performance.getStartAt(),
            performance.getEndAt(),
            performance.getStartReservationAt(),
            performance.getEndReservationAt(),
            Category.valueOf(performance.getCategory().toString()),
            performance.getTitle(),
            performance.getContent(),
            performance.getActing(),
            FilmRating.valueOf(performance.getFilmRating().toString()),
            performance.getCreatedAt(),
            performance.getUpdatedAt()
        );
    }
}
