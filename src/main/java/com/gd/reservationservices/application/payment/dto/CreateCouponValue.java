package com.gd.reservationservices.application.payment.dto;


import com.gd.reservationservices.domain.payment.Coupon;

import java.time.LocalDateTime;

public record CreateCouponValue(
        Long performanceId,
        Coupon.Type type,
        Integer discountValue,
        LocalDateTime expiredAt,
        Integer amount) {

}
