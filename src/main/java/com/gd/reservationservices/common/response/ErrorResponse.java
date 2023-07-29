package com.gd.reservationservices.common.response;


import lombok.Getter;

@Getter
public class ErrorResponse {
    private final Meta meta;

    public ErrorResponse(String errorCode, String errorMessage) {
        this.meta = new Meta.Fail(errorCode, errorMessage);
    }
}
