package com.gd.reservationservices.presentation.user.request;

import com.gd.reservationservices.application.user.command.CreateUser;

public record CreateUserRequest(
    String userId,
    String userPw,
    String name,
    int age,
    String email,
    String phone,
    String role
) {
    public CreateUser toValue() {
        return new CreateUser(
            this.userId,
            this.userPw,
            this.name,
            this.age,
            this.email,
            this.phone,
            this.role
        );
    }
}
