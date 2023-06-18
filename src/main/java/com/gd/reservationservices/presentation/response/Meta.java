package com.gd.reservationservices.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Meta {
    private final String result;
    private final String errorCode;
    private final String errorMessage;

    public static class Ok extends Meta {
        public Ok() {
            super("ok", null, null);
        }
    }

    public static class Fail extends Meta {
        public Fail(String errorCode, String errorMessage) {
            super("fail", errorCode, errorMessage);
        }
    }
}
