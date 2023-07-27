package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.ReservationCreateValue;
import com.gd.reservationservices.application.performance.exception.AlreadyReservedSeatException;
import com.gd.reservationservices.application.performance.exception.PerformanceNotFoundException;
import com.gd.reservationservices.application.performance.exception.ReservationNotFoundException;
import com.gd.reservationservices.application.performance.exception.ReservationNotMatchedException;
import com.gd.reservationservices.application.performance.value.LockKey;
import com.gd.reservationservices.application.user.exception.UserNotFoundException;
import com.gd.reservationservices.domain.performance.LockRepository;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.infrastructure.performance.PerformanceRepository;
import com.gd.reservationservices.infrastructure.performance.ReservationRepository;
import com.gd.reservationservices.infrastructure.performance.SeatRepository;
import com.gd.reservationservices.infrastructure.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReservationService {
    private final PerformanceRepository performanceRepository;
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final LockRepository lockRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void createReservation(Long performanceId, ReservationCreateValue requestValue) {
        LockKey lockKey = new LockKey(
                performanceId, requestValue.seatLocation(), requestValue.seatNumber()
        );

        if (!lockRepository.lock(lockKey.combination())){
            throw new AlreadyReservedSeatException();
        }
        eventPublisher.publishEvent(lockKey.combination());

        Performance performance = performanceRepository.findById(performanceId)
            .orElseThrow(PerformanceNotFoundException::new);

        Seat seat = seatRepository.findByPerformanceIdAndLocationAndNumber(
            performanceId, requestValue.seatLocation(), requestValue.seatNumber()
        ).orElseThrow(() -> new IllegalArgumentException("not found data"));

        if (seat.isReserved()) {
            throw new AlreadyReservedSeatException();
        }

        User user = userRepository.findById(requestValue.userId())
                .orElseThrow(UserNotFoundException::new);

        reservationRepository.save(
            new Reservation(
                user,
                performance,
                seat,
                LocalDateTime.now()
            )
        );

        seat.reserve();
    }

    public Page<Reservation> getAllReservations(Long performanceId, Pageable pageable) {
        Performance performance = performanceRepository.findById(performanceId)
            .orElseThrow(PerformanceNotFoundException::new);

        return reservationRepository.findAllByPerformance(performance, pageable);
    }

    public Reservation getReservation(Long performanceId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
            .orElseThrow(ReservationNotFoundException::new);

        if (!performanceId.equals(reservation.getPerformance().getId())) {
            throw new ReservationNotMatchedException();
        }

        return reservation;
    }
}
