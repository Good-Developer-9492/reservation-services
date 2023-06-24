package com.gd.reservationservices.infrastructure.performance;

import com.gd.reservationservices.domain.performance.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findByPerformanceIdAndLocationAndNumber(Long performanceId,
                                                           String location,
                                                           Integer number);
}
