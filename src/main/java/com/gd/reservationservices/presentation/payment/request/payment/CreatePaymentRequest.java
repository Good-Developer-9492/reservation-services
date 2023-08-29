package com.gd.reservationservices.presentation.payment.request.payment;

import com.gd.reservationservices.application.payment.command.CreatePaymentValue;

public record CreatePaymentRequest(
        Long userId,
        Long reservationId,
        Long couponId,
        Integer price
) {
    public CreatePaymentValue toValue() {
    return new CreatePaymentValue(
            userId,
            reservationId,
            couponId,
            price
    );
    }
}