package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.domain.payment.Point;
import com.gd.reservationservices.domain.payment.repository.PointRepository;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointDto;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointSum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Slf4j
public class PointService {
    private final PointRepository pointRepository;

    public PointSum searchGroupBy(Long userId) {
        return pointRepository.findGroupByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("INFORMATION_DOES_NOT_EXIST"));
    }

    public PointDto searchAllBy(Long userId) {
        UserPoint userPoints = new UserPoint(pointRepository.findAllByUserId(userId));

        return new PointDto(
                userId,
                userPoints.sum()
        );
    }

    public void create(Long userId, String type, Long value) {
        userId = new Random().nextLong(1, 10000);
        value = new Random().nextLong(-100, 100);
        if (value > 0) {
            type = "ADD";
        } else if (value < 0) {
            type = "USED";
        } else {
            return;
        }

        PointSum pointSum = pointRepository.findGroupByUserId(userId)
            .orElseThrow(() -> new IllegalArgumentException("INFORMATION_DOES_NOT_EXIST"));

        if (isTotalPointsLessThanZeroForUseType(type, pointSum)) {
            throw new IllegalArgumentException("INFORMATION_DOES_NOT_EXIST");
        }

        Point newPoint = new Point(userId, Point.Type.valueOf(type), value);
        pointRepository.save(newPoint);
    }

    private static boolean isTotalPointsLessThanZeroForUseType(String type, PointSum pointSum) {
        return Point.Type.USED.toString().equals(type) && pointSum.getPoint() <= 0;
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
