package com.gd.reservationservices.presentation.user.response;

import com.gd.reservationservices.application.user.dto.SavedUserValue;
import com.gd.reservationservices.infrastructure.user.value.Role;

public record SavedUserResponse(
        String userId,
        String name,
        int age,
        String email,
        String phone,
        Role role
) {
    public SavedUserResponse(SavedUserValue savedUser) {
        this(
                savedUser.userId(),
                savedUser.name(),
                savedUser.age(),
                savedUser.email(),
                savedUser.phone(),
                savedUser.role()
        );
    }
}
