package com.gd.reservationservices.presentation.response;


import com.gd.reservationservices.application.exception.ErrorCodeException;

public class ErrorResponse {
    private final Meta meta;

    public ErrorResponse(String errorCode, String errorMessage) {
        this.meta = new Meta.Fail(errorCode, errorMessage);
    }

    public ErrorResponse(ErrorCodeException e) {
        this(
            e.getKey(),
            e.getMessage()
        );
    }
}
