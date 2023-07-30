package com.gd.reservationservices.application.payment.command;

public record RefundPaymentValue(
        Long userId,
        Long reservationId,
        Long couponId,
        int price
){}