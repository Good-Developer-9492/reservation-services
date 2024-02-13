package com.gd.reservationservices.application.point.value;

import com.gd.reservationservices.domain.point.dto.SearchPoint;

import java.util.List;

public class UserPoints {
    private final List<SearchPoint> points;

    public UserPoints(List<SearchPoint> points) {
        this.points = points;
    }

    public long sum() {
        return this.points.stream()
            .mapToInt(pointDto -> Math.toIntExact(pointDto.getAmount()))
            .sum();
    }
}
