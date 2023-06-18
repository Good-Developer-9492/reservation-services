package com.gd.reservationservices.infrastructure.performance;

import com.gd.reservationservices.domain.performance.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
