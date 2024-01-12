package com.gd.reservationservices.infrastructure.payment.custom;

import com.gd.reservationservices.infrastructure.payment.custom.dto.PointSum;

import java.util.Optional;

public interface PointRepositoryCustom {
    Optional<PointSum> findGroupByUserId(Long id);
}
