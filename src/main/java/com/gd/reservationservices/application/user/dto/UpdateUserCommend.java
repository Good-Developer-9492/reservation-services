package com.gd.reservationservices.application.user.dto;

public record UpdateUserCommend(
        Long id,
        String userPw,
        String name,
        int age
) {
}
