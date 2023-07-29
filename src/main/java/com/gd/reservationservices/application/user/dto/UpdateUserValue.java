package com.gd.reservationservices.application.user.dto;

public record UpdateUserValue(
        String userPw,
        String name,
        int age
) {
}
