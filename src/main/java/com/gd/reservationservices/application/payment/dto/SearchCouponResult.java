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
                coupon.getSerialNumber(),
                coupon.getType().toString(),
                coupon.getDiscountValue(),
                coupon.getUsedAt(),
                coupon.getExpiredAt()
        );
    }
}