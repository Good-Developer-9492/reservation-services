package com.gd.reservationservices.common.advice;

import com.gd.reservationservices.application.user.exception.UserNotFoundException;
import com.gd.reservationservices.common.exception.ErrorCodeException;
import com.gd.reservationservices.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingAdvice {
    @ExceptionHandler(ErrorCodeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse unknownError(RuntimeException e) {
        return new ErrorResponse("internal-server-error", e.getMessage());
    }

    @ExceptionHandler({
            UserNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFound(ErrorCodeException e) {
        return new ErrorResponse(e);
    }

    @ExceptionHandler({
        IllegalArgumentException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequest(IllegalArgumentException e) {
        return new ErrorResponse("bad request", e.getMessage());
    }
}
