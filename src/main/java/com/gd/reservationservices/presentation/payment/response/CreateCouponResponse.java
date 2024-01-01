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
                result.serialNumber(),
                result.type(),
                result.discountValue(),
                result.expiredAt()
        );
    }
}