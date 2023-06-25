package com.gd.reservationservices.application.performance.exception;

import com.gd.reservationservices.common.exception.ErrorCodeException;

public class SeatNotFoundException extends ErrorCodeException {
    public SeatNotFoundException() {
        super(ErrorCode.SEAT_NOT_FOUND);
    }
}
