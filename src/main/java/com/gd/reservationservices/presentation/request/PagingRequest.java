package com.gd.reservationservices.presentation.request;

import java.util.Optional;

public record PagingRequest(Integer page, Integer perPage) {

    public PagingRequest(Integer page, Integer perPage) {
        this.page = Optional.ofNullable(page)
            .orElse(0);
        this.perPage = Optional.ofNullable(perPage)
            .orElse(10);
    }
}