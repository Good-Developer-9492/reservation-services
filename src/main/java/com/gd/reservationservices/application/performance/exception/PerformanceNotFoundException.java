package com.gd.reservationservices.application.performance.exception;

import com.gd.reservationservices.common.exception.ErrorCodeException;

public class PerformanceNotFoundException extends ErrorCodeException {
    public PerformanceNotFoundException() {
        super(ErrorCode.PERFORMANCE_NOT_FOUND);
    }
}
