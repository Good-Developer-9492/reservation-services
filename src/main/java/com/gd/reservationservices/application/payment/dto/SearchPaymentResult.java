package com.gd.reservationservices.application.payment.dto;

import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.user.User;

import java.time.LocalDateTime;

public record SearchPaymentResult(
        Long id,
        User user,
        Performance performance,
        Coupon coupon,
        Integer price,
        Integer discountPrice,
        LocalDateTime canceledAt
) {
}