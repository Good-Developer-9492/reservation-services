package com.gd.reservationservices.presentation.performance.request;

import com.gd.reservationservices.application.performance.dto.CreateReservationValue;

public record ReservationCreateRequest (
    Long userId,
    String seatLocation,
    Integer seatNumber
) {
    public CreateReservationValue toValue() {
        return new CreateReservationValue(
            this.userId, this.seatLocation, this.seatNumber
        );
    }
}
