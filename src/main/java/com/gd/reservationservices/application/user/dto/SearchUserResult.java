package com.gd.reservationservices.application.user.dto;


import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.infrastructure.user.value.Role;

public record SearchUserResult(
    String userId,
    String name,
    int age,
    String email,
    String phone,
    Role role
) {
    public SearchUserResult(User user) {
        this(
            user.getUserId(),
            user.getName(),
            user.getAge(),
            user.getEmail(),
            user.getPhone(),
            Role.valueOf(user.getRole().toString())
        );
    }
}
