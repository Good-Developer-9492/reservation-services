package com.gd.reservationservices.infrastructure.performance;

import com.gd.reservationservices.domain.performance.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
