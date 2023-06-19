package com.gd.reservationservices.application.user.dto;


import com.gd.reservationservices.infrastructure.user.value.Role;

public record SearchUser(
    String userId,
    String name,
    int age,
    String email,
    String phone,
    Role role
) {
}
