package com.gd.reservationservices.application.user.command;

import com.gd.reservationservices.domain.user.Role;
import com.gd.reservationservices.domain.user.User;

public record CreateUser(
    String userId,
    String userPw,
    String name,
    int age,
    String email,
    String phone,
    String role
) {
    public User toEntity() {
        return new User(
            this.userId,
            this.userPw,
            this.name,
            this.age,
            this.email,
            this.phone,
            Role.valueOf(this.role)
        );
    }
}
