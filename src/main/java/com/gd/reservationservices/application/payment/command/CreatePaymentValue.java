package com.gd.reservationservices.application.payment.command;

public record CreatePaymentValue(
        Long userID,
        Long reservationId,
        Long couponId,
        int price
){}