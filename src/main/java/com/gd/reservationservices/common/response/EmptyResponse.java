package com.gd.reservationservices.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EmptyResponse {
    private Meta meta;

    public static class Ok<T> extends EmptyResponse {
        public Ok() {
            super(new Meta.Ok());
        }
    }
}
