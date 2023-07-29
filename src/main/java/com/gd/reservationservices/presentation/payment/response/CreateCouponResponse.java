package com.gd.reservationservices.presentation.payment.response;

import com.gd.reservationservices.application.payment.dto.CreateCouponResult;

import java.time.LocalDateTime;

public record CreateCouponResponse(
        Long id,
        String performanceTitle,
        String code,
        String type,
        Integer value,
        LocalDateTime expiredAt
) {
    public CreateCouponResponse(CreateCouponResult result) {
        this(
                result.id(),
                result.performanceTitle(),
                result.code(),
                result.type(),
                result.value(),
                result.expiredAt()
        );
    }
}