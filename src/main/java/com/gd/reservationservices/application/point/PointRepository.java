package com.gd.reservationservices.application.point;

import com.gd.reservationservices.domain.point.dto.SearchPoint;
import com.gd.reservationservices.domain.point.dto.SearchPointGroupBy;

import java.util.List;
import java.util.Optional;

public interface PointRepository {
    Optional<SearchPointGroupBy> findGroupByUserId(Long userId);
    List<SearchPoint>  findAllByUserId(Long userId);
}
