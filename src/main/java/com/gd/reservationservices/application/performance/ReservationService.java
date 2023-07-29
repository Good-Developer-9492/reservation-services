package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.CreateReservationResult;
import com.gd.reservationservices.application.performance.dto.CreateReservationValue;
import com.gd.reservationservices.application.performance.dto.SearchReservationListResult;
import com.gd.reservationservices.application.performance.dto.SearchReservationResult;
import com.gd.reservationservices.application.performance.exception.*;
import com.gd.reservationservices.application.user.exception.UserNotFoundException;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.domain.performance.repository.PerformanceRepository;
import com.gd.reservationservices.domain.performance.repository.ReservationRepository;
import com.gd.reservationservices.domain.performance.repository.SeatRepository;
import com.gd.reservationservices.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReservationService {
    private final PerformanceRepository performanceRepository;
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateReservationResult create(Long performanceId, CreateReservationValue requestValue) {
        Performance performance = performanceRepository.findById(performanceId)
            .orElseThrow(IllegalArgumentException::new);

        Seat seat = seatRepository.findByPerformanceIdAndLocationAndNumber(
            performanceId, requestValue.seatLocation(), requestValue.seatNumber()
        ).orElseThrow(SeatNotFoundException::new);

        if (seat.isReserved()) {
            throw new AlreadyReservedSeatException();
        }

        User user = userRepository.findById(requestValue.userId())
            .orElseThrow(UserNotFoundException::new);

        Reservation reservation = reservationRepository.save(
            new Reservation(
                user,
                performance,
                seat,
                LocalDateTime.now()
            )
        );

        seat.reserve();

        return new CreateReservationResult(reservation);
    }

    public SearchReservationListResult searchAllBy(Long performanceId, Pageable pageable) {
        Performance performance = performanceRepository.findById(performanceId)
            .orElseThrow(PerformanceNotFoundException::new);

        return new SearchReservationListResult(
            reservationRepository.findAllByPerformance(performance, pageable)
        );
    }

    public SearchReservationResult searchBy(Long performanceId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(ReservationNotFoundException::new);

        if (!performanceId.equals(reservation.getPerformance().getId())) {
            throw new ReservationNotMatchedException();
        }

        return new SearchReservationResult(reservation);
    }
}
