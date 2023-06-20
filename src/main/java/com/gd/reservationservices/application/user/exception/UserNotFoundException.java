package com.gd.reservationservices.application.user.exception;

import com.gd.reservationservices.common.exception.ErrorCodeException;

public class UserNotFoundException extends ErrorCodeException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
