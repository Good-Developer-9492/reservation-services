package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Place;

public record PerformancePlace(
    String name,
    String location
) {
    public PerformancePlace(Place place) {
        this(
            place.getName(),
            place.getLocation()
        );
    }
}
