package com.gd.reservationservices.presentation.payment.response;

import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.performance.Performance;

import java.time.LocalDateTime;

public record SearchCouponResult(
        Long id,
        Performance performance,
        String code,
        Coupon.Type type,
        Integer value,
        LocalDateTime usedAt,
        LocalDateTime expiredAt
){
    public SearchCouponResult(Coupon coupon) {
        this(
                coupon.getId(),
                coupon.getPerformance(),
                coupon.getCode(),
                coupon.getType(),
                coupon.getValue(),
                coupon.getUsedAt(),
                coupon.getExpiredAt()
        );
    }
}
