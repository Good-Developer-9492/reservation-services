package com.gd.reservationservices.application.performance.dto;

public record ReservationCreateValue (
    Long userId,
    String seatLocation,
    Integer seatNumber
) {
}
