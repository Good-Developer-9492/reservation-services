package com.gd.reservationservices.presentation.user.request;

import com.gd.reservationservices.domain.user.Role;

public record CreateUserRequest(
    String userId,
    String userPw,
    String name,
    int age,
    String email,
    String phone,
    Role role
) {
}
