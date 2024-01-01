package com.gd.reservationservices.application.payment.dto;


import com.gd.reservationservices.domain.payment.Coupon;

import java.time.LocalDateTime;

public record UseCouponResult(
        Long id,
        String serialNumber,
        String type,
        Integer discountValue,
        LocalDateTime usedAt,
        LocalDateTime expiredAt
) {
    public UseCouponResult(Coupon coupon) {
        this(
                coupon.getId(),
                coupon.getSerialNumber(),
                coupon.getType().toString(),
                coupon.getDiscountValue(),
                coupon.getUsedAt(),
                coupon.getExpiredAt()
        );
    }
}
