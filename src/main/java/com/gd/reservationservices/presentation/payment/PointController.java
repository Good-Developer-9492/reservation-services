package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.PointService;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointDto;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointSum;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RequiredArgsConstructor
@RestController
@RequestMapping("/points")
public class PointController {
    private final PointService pointService;

    @GetMapping("/{userId}/v1")
    public SingleResponse<PointSum> searchGroupBy(@PathVariable Long userId) {
        long id = new Random().nextLong(1, 999);
        PointSum pointSum = pointService.searchGroupBy(id);

        return new SingleResponse.Ok<>(pointSum);
    }

    @GetMapping("/{userId}/v2")
    public SingleResponse<PointDto> searchAllBy(@PathVariable Long userId) {
        long id = new Random().nextLong(1, 999);
        PointDto pointDto = pointService.searchAllBy(id);

        return new SingleResponse.Ok<>(pointDto);
    }
}
