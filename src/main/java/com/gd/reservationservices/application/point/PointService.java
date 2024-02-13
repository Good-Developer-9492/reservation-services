package com.gd.reservationservices.application.point;

import com.gd.reservationservices.application.point.value.UserPoints;
import com.gd.reservationservices.domain.point.dto.SearchPointGroupBy;
import com.gd.reservationservices.infrastructure.point.dto.SearchPointResult;
import com.gd.reservationservices.domain.point.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class PointService {
    private final PointRepository pointRepository;

    public SearchPointResult searchGroupBy(Long userId) {
        SearchPointGroupBy userPoint = pointRepository.findGroupByUserId(userId)
            .orElseThrow(() -> new IllegalArgumentException("NOT FOUND"));

        return new SearchPointResult(
            userPoint.getUserId(),
//            userPoint.getAmount()
                10L
        );
    }

    public SearchPointResult searchAllBy(Long userId) {
        UserPoints userPoints = new UserPoints(pointRepository.findAllByUserId(userId));

        return new SearchPointResult(
            userId,
            userPoints.sum()
        );
    }
}
