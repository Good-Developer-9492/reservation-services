package com.gd.reservationservices.presentation.user.response;

import com.gd.reservationservices.application.user.dto.UpdateUserResult;
import com.gd.reservationservices.domain.user.Role;

public record UpdateUserResponse(
    String userId,
    String name,
    int age,
    String email,
    String phone,
    Role role
) {
    public UpdateUserResponse(UpdateUserResult updateUserResult) {
        this(
            updateUserResult.userId(),
            updateUserResult.name(),
            updateUserResult.age(),
            updateUserResult.email(),
            updateUserResult.phone(),
            updateUserResult.role()
        );
    }
}
