package com.gd.reservationservices.common.response;


import com.gd.reservationservices.common.exception.ErrorCodeException;
import lombok.Getter;

@Getter
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
