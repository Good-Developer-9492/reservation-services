package com.gd.reservationservices.presentation.performance.response;

import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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
}
