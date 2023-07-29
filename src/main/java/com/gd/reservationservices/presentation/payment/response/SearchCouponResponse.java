package com.gd.reservationservices.presentation.payment.response;

import com.gd.reservationservices.application.payment.dto.SearchCouponResult;

import java.time.LocalDateTime;

public record SearchCouponResponse(
        Long id,
        String performanceTitle,
        String code,
        String type,
        Integer value,
        LocalDateTime usedAt,
        LocalDateTime expiredAt
) {
    public SearchCouponResponse(SearchCouponResult coupon) {
        this(
                coupon.id(),
                coupon.performanceTitle(),
                coupon.code(),
                coupon.type(),
                coupon.value(),
                coupon.usedAt(),
                coupon.expiredAt()
        );
    }
}
