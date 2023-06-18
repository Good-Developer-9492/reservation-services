package com.gd.reservationservices.application.user.command;

import com.gd.reservationservices.domain.user.Role;

public record CreateUser(
    String userId,
    String userPw,
    String name,
    int age,
    String email,
    String phone,
    Role role
) {
}
