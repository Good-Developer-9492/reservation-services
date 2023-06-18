package com.gd.reservationservices.application.exception;

public abstract class ErrorCodeException extends RuntimeException {
    private final ErrorCode errorCode;

    public ErrorCodeException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCodeException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCodeException(ErrorCode errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public String getKey() {
        return errorCode.getKey();
    }

    public String getMessage() {
        return errorCode.getMessage();
    }

    protected enum ErrorCode {
        USER_NOT_FOUND("사용자 정보가 존재하지 않습니다.");

        private final String message;

        ErrorCode(String message) {
            this.message = message;
        }

        public String getKey() {
            return name().replace("_", "-").toLowerCase();
        }

        public String getMessage() {
            return message;
        }
    }
}