package com.gd.reservationservices.infrastructure.point.custom;

import com.gd.reservationservices.domain.point.dto.SearchPointGroupBy;

import java.util.Optional;

public interface PointRepositoryCustom {
    Optional<SearchPointGroupBy> findGroupByUserId(Long id);
}
