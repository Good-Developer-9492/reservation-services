package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.CreateReservationResult;
import com.gd.reservationservices.application.performance.dto.CreateReservationValue;
import com.gd.reservationservices.application.performance.dto.SearchReservationListResult;
import com.gd.reservationservices.application.performance.dto.SearchReservationResult;
import com.gd.reservationservices.common.exception.ErrorCode;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.infrastructure.performance.PerformanceRepository;
import com.gd.reservationservices.infrastructure.performance.ReservationRepository;
import com.gd.reservationservices.infrastructure.performance.SeatRepository;
import com.gd.reservationservices.infrastructure.user.UserRepository;
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
            .orElseThrow(() -> new IllegalArgumentException(ErrorCode.PERFORMANCE_NOT_FOUND.getMessage()));

        Seat seat = seatRepository.findByPerformanceIdAndLocationAndNumber(
            performanceId, requestValue.seatLocation(), requestValue.seatNumber()
        ).orElseThrow(() -> new IllegalArgumentException(ErrorCode.SEAT_NOT_FOUND.getMessage()));

        if (seat.isReserved()) {
            throw new IllegalArgumentException(ErrorCode.ALREADY_RESERVED_SEAT.getMessage());
        }

        User user = userRepository.findById(requestValue.userId())
            .orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUND.getMessage()));

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
            .orElseThrow(() -> new IllegalArgumentException(ErrorCode.PERFORMANCE_NOT_FOUND.getMessage()));

        return new SearchReservationListResult(
            reservationRepository.findAllByPerformance(performance, pageable)
        );
    }

    public SearchReservationResult searchBy(Long performanceId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorCode.RESERVATION_NOT_FOUND.getMessage()));

        if (!performanceId.equals(reservation.getPerformance().getId())) {
            throw new IllegalArgumentException(ErrorCode.RESERVATION_NOT_MATCHED_PERFORMANCE.getMessage());
        }

        return new SearchReservationResult(reservation);
    }
}
