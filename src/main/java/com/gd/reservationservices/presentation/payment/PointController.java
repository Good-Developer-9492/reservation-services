package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.PointService;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointDto;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointSum;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping("/points")
public class PointController {
    private final PointService pointService;

    @GetMapping("/{userId}/v1")
    public SingleResponse<PointSum> searchGroupBy(@PathVariable Long userId) {
        long id = new Random().nextLong(1, 10000);
        PointSum pointSum = pointService.searchGroupBy(id);

        return new SingleResponse.Ok<>(pointSum);
    }

    @GetMapping("/{userId}/v2")
    public SingleResponse<PointDto> searchAllBy(@PathVariable Long userId) {
        long id = new Random().nextLong(1, 10000);
        PointDto pointDto = pointService.searchAllBy(id);

        return new SingleResponse.Ok<>(pointDto);
    }

    @PostMapping("/{userId}")
    public String create(@PathVariable Long userId,
                         @RequestBody CreatePoint createPoint) {
        pointService.create(userId, createPoint.getType(), createPoint.getValue());
        return "ok";
    }

    static class CreatePoint{
        private final String type;
        private final Long value;

        public CreatePoint(String type, Long value) {
            this.type = type;
            this.value = value;
        }

        public String getType() {
            return type;
        }

        public Long getValue() {
            return value;
        }
    }
}
