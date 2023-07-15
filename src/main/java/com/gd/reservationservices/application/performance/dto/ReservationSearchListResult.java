package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Reservation;
import org.springframework.data.domain.Page;

import java.util.List;

public record ReservationSearchListResult(
    List<Reservation> reservations,
    Long totalCount,
    Integer currentPage,
    Integer perPage,
    Integer totalPage
) {
    public ReservationSearchListResult(Page<Reservation> reservations) {
        this(
            reservations.getContent(),
            reservations.getTotalElements(),
            reservations.getPageable().getPageNumber(),
            reservations.getPageable().getPageSize(),
            reservations.getTotalPages()
        );
    }
}
