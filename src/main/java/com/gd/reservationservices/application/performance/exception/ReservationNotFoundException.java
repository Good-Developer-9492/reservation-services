package com.gd.reservationservices.application.performance.exception;

import com.gd.reservationservices.common.exception.ErrorCodeException;

public class ReservationNotFoundException extends ErrorCodeException {
    public ReservationNotFoundException() {
        super(ErrorCode.RESERVATION_NOT_FOUND);
    }
}
