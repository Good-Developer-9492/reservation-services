package com.gd.reservationservices.presentation.payment.response;

import com.gd.reservationservices.application.payment.dto.UseCouponResult;

import java.time.LocalDateTime;

public record UseCouponResponse(
        Long id,
        String serialNumber,
        String type,
        Integer discountValue,
        LocalDateTime usedAt,
        LocalDateTime expiredAt
) {
    public UseCouponResponse(UseCouponResult coupon) {
        this(
                coupon.id(),
                coupon.serialNumber(),
                coupon.type(),
                coupon.discountValue(),
                coupon.usedAt(),
                coupon.expiredAt()
        );
    }
}
