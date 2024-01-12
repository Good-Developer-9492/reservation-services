package com.gd.reservationservices.infrastructure.payment.custom.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PointSum {
    private final Long userId;
    private final Long point;

    @QueryProjection
    public PointSum(Long userId, Long point) {
        this.userId = userId;
        this.point = point;
    }
}
