package com.gd.reservationservices.infrastructure.payment.custom.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PointDto {
    private final Long userId;
    private final Long value;

    @QueryProjection
    public PointDto(Long userId, Long value) {
        this.userId = userId;
        this.value = value;
    }
}
