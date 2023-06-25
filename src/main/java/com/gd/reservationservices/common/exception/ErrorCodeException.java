package com.gd.reservationservices.common.exception;

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
        USER_NOT_FOUND("사용자 정보가 존재하지 않습니다."),
        PERFORMANCE_NOT_FOUND("공연 정보가 존재하지 않습니다."),
        RESERVATION_NOT_FOUND("예매 정보가 존재하지 않습니다."),
        RESERVATION_NOT_MATCHED_PERFORMANCE("해당 공연의 예매 정보가 아닙니다."),
        SEAT_NOT_FOUND("좌석 정보가 존재하지 않습니다."),
        ALREADY_RESERVED_SEAT("이미 선택된 좌석입니다.");

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
