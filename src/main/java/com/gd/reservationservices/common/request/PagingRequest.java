package com.gd.reservationservices.common.request;

import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public record PagingRequest(Integer page, Integer perPage) {

    public PagingRequest(Integer page, Integer perPage) {
        this.page = Optional.ofNullable(page)
            .orElse(0);
        this.perPage = Optional.ofNullable(perPage)
            .orElse(10);
    }

    public PageRequest toPageable() {
        return PageRequest.of(this.page, this.perPage);
    }
}
