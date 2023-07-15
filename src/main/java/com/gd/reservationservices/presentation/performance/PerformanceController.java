package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.PerformanceService;
import com.gd.reservationservices.application.performance.dto.CreatePerformanceResult;
import com.gd.reservationservices.application.performance.dto.SearchPerformanceResult;
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
        CreatePerformanceResult createPerformanceResult =
            performanceService.create(createPerformanceRequest.toValue());

        return new SingleResponse.Ok<>(
            new CreatePerformanceResponse(createPerformanceResult));
    }

    @GetMapping("/business/{id}")
    public SingleResponse<FindPerformanceResponse> searchBy(@PathVariable Long id) {
        SearchPerformanceResult searchPerformanceResult = performanceService.searchBy(id);

        return new SingleResponse.Ok<>(
            new FindPerformanceResponse(searchPerformanceResult)
        );
    }
}
