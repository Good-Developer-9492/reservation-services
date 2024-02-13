package com.gd.reservationservices.presentation.point;

import com.gd.reservationservices.application.point.PointService;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.infrastructure.point.dto.SearchPointResult;
import com.gd.reservationservices.presentation.point.response.SearchPointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/points")
public class PointController {
    private final PointService pointService;

    @GetMapping("/{userId}/v1")
    public SingleResponse<SearchPointResponse> searchGroupBy(@PathVariable Long userId) {
        SearchPointResult searchPoint = pointService.searchGroupBy(userId);

        return new SingleResponse.Ok<>(
            new SearchPointResponse(
                searchPoint.userId(),
                searchPoint.availablePoints()
            )
        );
    }

    @GetMapping("/{userId}/v2")
    public SingleResponse<SearchPointResponse> searchAllBy(@PathVariable Long userId) {
        SearchPointResult searchPoint = pointService.searchAllBy(userId);

        return new SingleResponse.Ok<>(
            new SearchPointResponse(
                searchPoint.userId(),
                searchPoint.availablePoints()
            )
        );
    }
}

