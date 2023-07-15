package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.domain.payment.Coupon;

import java.time.LocalDateTime;

public record CreateCouponResult(
        Long id,
        String performanceTitle,
        String code,
        String type,
        Integer value,
        LocalDateTime expiredAt
) {
    public CreateCouponResult(Coupon coupon) {
        this(
                coupon.getId(),
                coupon.getPerformance().getTitle(),
                coupon.getCode(),
                coupon.getType().toString(),
                coupon.getValue(),
                coupon.getExpiredAt()
        );
    }
}