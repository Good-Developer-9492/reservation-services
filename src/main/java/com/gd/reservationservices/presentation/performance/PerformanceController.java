package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.PerformanceService;
import com.gd.reservationservices.application.performance.dto.CreatePerformance;
import com.gd.reservationservices.application.performance.dto.CreatePerformanceCommend;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.performance.request.CreatePerformanceRequest;
import com.gd.reservationservices.presentation.performance.response.CreatePerformanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performance")
public class PerformanceController {
    private final PerformanceService performanceService;

    /**
     * 공연 정보 등록
     * -같은 시간 장소에 등록된 공여니 있으면 exception
     * -등록된 공연 없으면 next
     */
    @PostMapping
    public SingleResponse<CreatePerformanceResponse> create(@RequestBody CreatePerformanceRequest createPerformanceRequest) {
        CreatePerformance createPerformance =
                performanceService.create(new CreatePerformanceCommend(
                        createPerformanceRequest.placeId(),
                        createPerformanceRequest.category(),
                        createPerformanceRequest.startAt(),
                        createPerformanceRequest.endAt(),
                        createPerformanceRequest.startReservationAt(),
                        createPerformanceRequest.endReservationAt(),
                        createPerformanceRequest.title(),
                        createPerformanceRequest.content(),
                        createPerformanceRequest.acting(),
                        createPerformanceRequest.filmRating()
                ));

        return new SingleResponse.Ok<>(
                new CreatePerformanceResponse(
                        createPerformance.place(),
                        createPerformance.category(),
                        createPerformance.startAt(),
                        createPerformance.startReservationAt(),
                        createPerformance.endReservationAt(),
                        createPerformance.createdAt(),
                        createPerformance.updatedAt(),
                        createPerformance.title(),
                        createPerformance.content(),
                        createPerformance.acting(),
                        createPerformance.filmRating()
                ));
    }

}
