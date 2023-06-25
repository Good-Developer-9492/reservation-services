package com.gd.reservationservices.presentation.performance.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gd.reservationservices.application.performance.dto.CreatePerformanceCommand;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

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
    String filmRating,
    int price,
    Collection<SeatRequest> seats
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
            this.filmRating,
            this.price,
            this.seats.stream()
                .map(SeatRequest::toValue)
                .collect(Collectors.toList())
        );
    }

    public record SeatRequest(
        String location,
        int seatCount
    ) {
        public CreatePerformanceCommand.SeatCommand toValue() {
            return new CreatePerformanceCommand.SeatCommand(
                this.location,
                this.seatCount
            );
        }

    }
}
