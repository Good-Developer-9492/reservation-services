package com.gd.reservationservices.domain.point.repository;

import com.gd.reservationservices.domain.point.Point;
import com.gd.reservationservices.domain.point.dto.SearchPoint;
import com.gd.reservationservices.infrastructure.point.custom.PointRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointRepository extends JpaRepository<Point, Long>, PointRepositoryCustom {
    List<SearchPoint> findAllByUserId(Long id);
}
