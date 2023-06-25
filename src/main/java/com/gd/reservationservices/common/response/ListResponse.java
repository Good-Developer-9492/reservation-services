package com.gd.reservationservices.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListResponse<T> {
    private final List<T> items;
    private final Meta meta;
    private final Paging paging;

    public static class Ok<T> extends ListResponse<T> {
        public Ok(List<T> items, Paging paging) {
            super(items, new Meta.Ok(), paging);
        }
    }
}
