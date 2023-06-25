package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.domain.payment.Coupon;

import java.time.LocalDateTime;

public record UpdateCouponValue(
        Long id,
        Long performanceId,
        Coupon.Type type,
        Integer value,
        LocalDateTime expiredAt) {
}