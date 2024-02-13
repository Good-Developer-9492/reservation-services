package com.gd.reservationservices.domain.point.dto;

import com.querydsl.core.annotations.QueryProjection;

public class SearchPointGroupBy {
    private final Long userId;
    private final Long amount;

    @QueryProjection
    public SearchPointGroupBy(Long userId, Long amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAmount() {
        return amount;
    }
}
