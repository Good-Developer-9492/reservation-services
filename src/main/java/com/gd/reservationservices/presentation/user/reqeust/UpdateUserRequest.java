package com.gd.reservationservices.presentation.user.reqeust;

public record UpdateUserRequest(
        Long id,
        String userPw,
        String name,
        int age
) {
}
