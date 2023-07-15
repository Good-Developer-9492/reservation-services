package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.User;

import java.time.LocalDateTime;

public record CreateReservationResult (
    Long id,
    User user,
    Performance performance,
    Seat seat,
    LocalDateTime reservedAt
) {
    public CreateReservationResult(Reservation reservation) {
        this(
            reservation.getId(),
            reservation.getUser(),
            reservation.getPerformance(),
            reservation.getSeat(),
            reservation.getReservedAt()
        );
    }
}
