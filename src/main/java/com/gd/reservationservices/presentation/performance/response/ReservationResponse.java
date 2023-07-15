package com.gd.reservationservices.presentation.performance.response;

import com.gd.reservationservices.application.performance.dto.ReservationSearchResult;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.User;

import java.time.LocalDateTime;

public record ReservationResponse(
    Long id,
    User user,
    Seat seat,
    LocalDateTime reservedAt
) {
    public ReservationResponse(Reservation reservation) {
        this(
            reservation.getId(),
            reservation.getUser(),
            reservation.getSeat(),
            reservation.getReservedAt()
        );
    }

    public ReservationResponse(ReservationSearchResult result) {
        this(
            result.id(),
            result.user(),
            result.seat(),
            result.reservedAt()
        );
    }
}
