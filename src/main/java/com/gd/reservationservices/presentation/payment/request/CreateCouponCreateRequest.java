package com.gd.reservationservices.presentation.payment.request;

import com.gd.reservationservices.application.payment.CreateCouponValue;
import com.gd.reservationservices.domain.payment.Coupon;

import java.time.LocalDateTime;

public record CreateCouponCreateRequest(
        Long performanceId,
        Coupon.Type type,
        Integer value,
        LocalDateTime expiredAt,
        Integer amount) {

    public CreateCouponValue toValue() {
        return new CreateCouponValue(
                this.performanceId(),
                this.type(),
                this.value(),
                this.expiredAt(),
                this.amount()
        );
    }

}