package com.gd.reservationservices.presentation.payment.request.payment;

import com.gd.reservationservices.application.payment.command.CreatePaymentValue;

public record RefundPaymentRequest(
        Long reservationId,
        Long couponId,
        Integer price
) {
    public CreatePaymentValue toValue() {
    return new CreatePaymentValue(
            null,
            reservationId,
            couponId,
            price
    );
    }
}