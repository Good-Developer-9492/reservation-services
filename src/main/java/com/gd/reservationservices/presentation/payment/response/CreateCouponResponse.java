package com.gd.reservationservices.presentation.payment.response;

import com.gd.reservationservices.application.payment.CreateCouponResult;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.performance.Performance;

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