package com.gd.reservationservices.application.performance.exception;

import com.gd.reservationservices.common.exception.ErrorCodeException;

public class ReservationNotMatchedException extends ErrorCodeException {
    public ReservationNotMatchedException() {
        super(ErrorCode.RESERVATION_NOT_MATCHED_PERFORMANCE);
    }
}
