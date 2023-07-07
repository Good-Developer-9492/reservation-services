package com.gd.reservationservices.infrastructure.performance;

import com.gd.reservationservices.domain.performance.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
//    @QueryHints(@QueryHint(name = "javax.persistence.lock.timeout",value = "0")) // nowait
//    @Lock(value = LockModeType.OPTIMISTIC)
    Optional<Seat> findByPerformanceIdAndLocationAndNumber(Long performanceId,
                                                           String location,
                                                           Integer number);
}
