package com.gd.reservationservices.presentation.payment.request;

import com.gd.reservationservices.domain.payment.Coupon;

import java.time.LocalDateTime;

public record CreateCouponCreateRequest(
        Long performanceId,
        Coupon.Type type,
        Integer value,
        LocalDateTime expiredAt,
        Integer amount) {
}