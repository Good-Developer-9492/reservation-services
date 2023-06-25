package com.gd.reservationservices.application.performance.exception;

import com.gd.reservationservices.common.exception.ErrorCodeException;

public class AlreadyReservedSeatException extends ErrorCodeException {
    public AlreadyReservedSeatException() {
        super(ErrorCode.ALREADY_RESERVED_SEAT);
    }
}
