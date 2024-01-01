package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Place;

public record CreatePlaceValue(
    String name,
    String location,
    Integer maxSeat
) {
    public Place toEntity() {
        return new Place(
            this.name,
            this.location,
            this.maxSeat
        );
    }
}
