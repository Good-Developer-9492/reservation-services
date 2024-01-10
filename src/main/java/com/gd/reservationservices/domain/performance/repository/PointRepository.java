package com.gd.reservationservices.domain.performance.repository;

import com.gd.reservationservices.domain.payment.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {
}
