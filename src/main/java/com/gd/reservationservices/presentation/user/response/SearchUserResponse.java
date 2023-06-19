package com.gd.reservationservices.presentation.user.response;

import com.gd.reservationservices.domain.user.Role;

public record SearchUserResponse(
        String userId,
        String name,
        int age,
        String email,
        String phone,
        Role role
) {
}
