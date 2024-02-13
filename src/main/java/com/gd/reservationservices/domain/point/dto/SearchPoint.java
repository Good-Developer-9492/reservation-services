package com.gd.reservationservices.domain.point.dto;

public class SearchPoint {
    private final Long userId;
    private final Long amount;

    public SearchPoint(Long amount) {
        this.userId = 1L;
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getAmount() {
        return amount;
    }
}
