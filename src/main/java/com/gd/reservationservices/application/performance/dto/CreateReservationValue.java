package com.gd.reservationservices.application.performance.dto;

public record CreateReservationValue(
    Long userId,
    String seatLocation,
    Integer seatNumber
) {
}
