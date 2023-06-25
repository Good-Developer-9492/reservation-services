package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.PerformanceService;
import com.gd.reservationservices.application.performance.dto.CreatePerformance;
import com.gd.reservationservices.application.performance.dto.FindPerformance;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.performance.request.CreatePerformanceRequest;
import com.gd.reservationservices.presentation.performance.response.CreatePerformanceResponse;
import com.gd.reservationservices.presentation.performance.response.FindPerformanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performance")
public class PerformanceController {
    private final PerformanceService performanceService;

    @PostMapping
    public SingleResponse<CreatePerformanceResponse> create(@RequestBody CreatePerformanceRequest createPerformanceRequest) {
        CreatePerformance createPerformance =
            performanceService.create(createPerformanceRequest.toValue());

        return new SingleResponse.Ok<>(
            new CreatePerformanceResponse(createPerformance));
    }

    @GetMapping("/business/{id}")
    public SingleResponse<FindPerformanceResponse> find(@PathVariable Long id) {
        FindPerformance findPerformance = performanceService.find(id);

        return new SingleResponse.Ok<>(
            new FindPerformanceResponse(findPerformance)
        );
    }
}
