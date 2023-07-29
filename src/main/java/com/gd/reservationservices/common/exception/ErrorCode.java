package com.gd.reservationservices.common.exception;

public enum ErrorCode {
    USER_NOT_FOUND("사용자 정보가 존재하지 않습니다."),
    ALREADY_REGISTERED_USER("이미 등록된 아이디 입니다."),

    PERFORMANCE_NOT_FOUND("공연 정보가 존재하지 않습니다."),
    PERFORMANCE_NOT_AVAILABLE_DATETIME("해당 시간에 공연을 등록할 수 없습니다."),
    PERFORMANCE_EXCEED_MAX_SEAT_ON_PLACE("등록 좌석 정보가 공연장 최대 좌석 수를 초과하였습니다."),

    PLACE_NOT_FOUND("공연장 정보가 존재하지 않습니다."),

    SEAT_NOT_FOUND("좌석 정보가 존재하지 않습니다."),

    RESERVATION_NOT_FOUND("예매 정보가 존재하지 않습니다."),
    RESERVATION_NOT_MATCHED_PERFORMANCE("해당 공연의 예매 정보가 아닙니다."),
    ALREADY_RESERVED_SEAT("이미 선택된 좌석입니다."),

    COUPON_NOT_FOUND("쿠폰을 찾을 수 없습니다."),
    COUPON_DISCOUNT_NOT_AVAILABLE_EXCEED_PERFORMANCE_PRICE("할인 금액은 공연가격(비율)을 초과할 수 없습니다.");

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
