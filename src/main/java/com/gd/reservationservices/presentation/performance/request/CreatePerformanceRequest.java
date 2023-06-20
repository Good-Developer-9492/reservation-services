package com.gd.reservationservices.presentation.performance.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gd.reservationservices.application.performance.dto.CreatePerformanceCommand;

import java.time.LocalDateTime;

public record CreatePerformanceRequest(
    Long placeId,
    String category,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime startAt,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime endAt,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime startReservationAt,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime endReservationAt,
    String title,
    String content,
    String acting,
    String filmRating
) {
    public CreatePerformanceCommand toValue() {
        return new CreatePerformanceCommand(
            this.placeId,
            this.category,
            this.startAt,
            this.endAt,
            this.startReservationAt,
            this.endReservationAt,
            this.title,
            this.content,
            this.acting,
            this.filmRating
        );
    }
}
