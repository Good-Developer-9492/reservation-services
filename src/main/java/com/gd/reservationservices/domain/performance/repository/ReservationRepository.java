package com.gd.reservationservices.domain.performance.repository;

import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Page<Reservation> findAllByPerformance(Performance performance, Pageable pageable);
}
