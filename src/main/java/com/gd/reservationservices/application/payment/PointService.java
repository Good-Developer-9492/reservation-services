package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.domain.payment.repository.PointRepository;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointDto;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointSum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PointService {
    private final PointRepository pointRepository;

    public PointSum searchGroupBy(Long userId) {
        return pointRepository.findGroupByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND"));
    }

    public PointDto searchAllBy(Long userId) {
        UserPoint userPoints = new UserPoint(pointRepository.findAllByUserId(userId));

        return new PointDto(
            userId,
            userPoints.sum()
        );
    }

    private static class UserPoint {
        private final List<PointDto> points;

        public UserPoint(List<PointDto> points) {
            this.points = points;
        }

        private long sum() {
            return this.points.stream()
                    .mapToInt(pointDto -> Math.toIntExact(pointDto.getValue()))
                    .sum();
        }
    }
}
