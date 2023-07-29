package com.gd.reservationservices.application.payment.dto;

import com.gd.reservationservices.domain.payment.Coupon;

import java.time.LocalDateTime;

public record SearchCouponResult(
        Long id,
        String performanceTitle,
        String code,
        String type,
        Integer value,
        LocalDateTime usedAt,
        LocalDateTime expiredAt
) {
    public SearchCouponResult(Coupon coupon) {
        this(
                coupon.getId(),
                coupon.getPerformance().getTitle(),
                coupon.getCode(),
                coupon.getType().toString(),
                coupon.getValue(),
                coupon.getUsedAt(),
                coupon.getExpiredAt()
        );
    }
}