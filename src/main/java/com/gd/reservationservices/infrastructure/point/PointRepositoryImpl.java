package com.gd.reservationservices.infrastructure.point;

import com.gd.reservationservices.application.point.PointRepository;
import com.gd.reservationservices.domain.point.dto.SearchPoint;
import com.gd.reservationservices.domain.point.dto.SearchPointGroupBy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PointRepositoryImpl implements PointRepository {
    private final PointJpaRepository pointJpaRepository;

    @Override
    public Optional<SearchPointGroupBy> findGroupByUserId(Long userId) {
        return pointJpaRepository.findGroupByUserId(userId);
    }

    @Override
    public List<SearchPoint> findAllByUserId(Long userId) {
        return pointJpaRepository.findAllByUserId(userId);
    }
}
