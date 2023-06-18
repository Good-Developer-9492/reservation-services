package com.gd.reservationservices.presentation.response;

import lombok.AllArgsConstructor;

import java.util.List;

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
