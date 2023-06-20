package com.gd.reservationservices.presentation.user.response;

import com.gd.reservationservices.application.user.dto.UpdateUser;
import com.gd.reservationservices.domain.user.Role;

public record UpdateUserResponse(
    String userId,
    String name,
    int age,
    String email,
    String phone,
    Role role
) {
    public UpdateUserResponse(UpdateUser updateUser) {
        this(
            updateUser.userId(),
            updateUser.name(),
            updateUser.age(),
            updateUser.email(),
            updateUser.phone(),
            updateUser.role()
        );
    }
}
