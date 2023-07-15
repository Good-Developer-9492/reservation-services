package com.gd.reservationservices.application.payment;

import java.time.LocalDateTime;

public record CreateCouponResult(
        Long in,
        String performanceTitle,
        String code,
        String type,
        Integer value,
        LocalDateTime expiredAt
){}