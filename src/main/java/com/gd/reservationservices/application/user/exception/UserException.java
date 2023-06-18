package com.gd.reservationservices.application.user.exception;

import com.gd.reservationservices.application.exception.ErrorCodeException;

public class UserException extends ErrorCodeException {
    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public UserException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
