package com.gd.reservationservices.infrastructure.point;

import com.gd.reservationservices.domain.point.dto.SearchPoint;
import com.gd.reservationservices.infrastructure.point.custom.PointRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointJpaRepository extends JpaRepository<PointEntity, Long>, PointRepositoryCustom {
    List<SearchPoint> findAllByUserId(Long id);
}
