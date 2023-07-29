package com.gd.reservationservices.presentation.user.reqeust;

import com.gd.reservationservices.application.user.dto.UpdateUserValue;

public record UpdateUserRequest(
    String userPw,
    String name,
    int age
) {
    public UpdateUserValue toValue() {
        return new UpdateUserValue(
            this.userPw,
            this.name,
            this.age
        );
    }
}
