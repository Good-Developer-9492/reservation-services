package com.gd.reservationservices.domain.payment.repository;

import com.gd.reservationservices.domain.payment.Point;
import com.gd.reservationservices.infrastructure.payment.custom.PointRepositoryCustom;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long>, PointRepositoryCustom {
    List<PointDto> findAllByUserId(Long id);
}
