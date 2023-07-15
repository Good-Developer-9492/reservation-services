package com.gd.reservationservices.presentation.performance.response;

import com.gd.reservationservices.application.performance.dto.CreateReservationResult;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.User;

import java.time.LocalDateTime;

public record CreateReservationResponse (
    Long id,
    User user,
    Performance performance,
    Seat seat,
    LocalDateTime reservedAt
) {
    public CreateReservationResponse(CreateReservationResult result) {
        this(
            result.id(),
            result.user(),
            result.performance(),
            result.seat(),
            result.reservedAt()
        );
    }
}
