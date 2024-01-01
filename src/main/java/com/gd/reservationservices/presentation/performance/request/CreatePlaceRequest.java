package com.gd.reservationservices.presentation.performance.request;

import com.gd.reservationservices.application.performance.dto.CreatePlaceValue;

public record CreatePlaceRequest(
    String name,
    String location,
    Integer maxSeat
) {
    public CreatePlaceValue toVValue() {
        return new CreatePlaceValue(
            this.name,
            this.location,
            this.maxSeat
        );
    }
}
