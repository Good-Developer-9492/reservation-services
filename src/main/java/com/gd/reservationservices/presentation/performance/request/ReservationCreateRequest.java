package com.gd.reservationservices.presentation.performance.request;

import com.gd.reservationservices.application.performance.dto.ReservationCreateValue;

public record ReservationCreateRequest (
    Long userId,
    String seatLocation,
    Integer seatNumber
) {
    public ReservationCreateValue toValue() {
        return new ReservationCreateValue(
            this.userId, this.seatLocation, this.seatNumber
        );
    }
}
