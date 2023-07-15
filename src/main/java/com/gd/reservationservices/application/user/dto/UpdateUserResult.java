package com.gd.reservationservices.application.user.dto;

import com.gd.reservationservices.domain.user.Role;
import com.gd.reservationservices.domain.user.User;

public record UpdateUserResult(
        String userId,
        String name,
        int age,
        String email,
        String phone,
        Role role
) {
    public UpdateUserResult(User user) {
        this(
            user.getUserId(),
            user.getName(),
            user.getAgw(),
            user.getEmail(),
            user.getPhone(),
            user.getRole()
        );
    }
}
