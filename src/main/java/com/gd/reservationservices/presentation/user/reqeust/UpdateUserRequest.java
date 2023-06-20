package com.gd.reservationservices.presentation.user.reqeust;

import com.gd.reservationservices.application.user.dto.UpdateUserCommend;

public record UpdateUserRequest(
    Long id,
    String userPw,
    String name,
    int age
) {
    public UpdateUserCommend toValue() {
        return new UpdateUserCommend(
            this.id,
            this.userPw,
            this.name,
            this.age
        );
    }
}
