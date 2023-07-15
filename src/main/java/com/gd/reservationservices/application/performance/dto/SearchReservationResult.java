package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.User;

import java.time.LocalDateTime;

public record SearchReservationResult(
    Long id,
    User user,
    Seat seat,
    LocalDateTime reservedAt
) {
    public SearchReservationResult(Reservation reservation) {
        this(
            reservation.getId(),
            reservation.getUser(),
            reservation.getSeat(),
            reservation.getReservedAt()
        );
    }
}
