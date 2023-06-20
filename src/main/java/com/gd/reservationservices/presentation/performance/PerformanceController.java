package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.PerformanceService;
import com.gd.reservationservices.application.performance.dto.CreatePerformance;
import com.gd.reservationservices.application.performance.dto.FindPerformance;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.performance.request.CreatePerformanceRequest;
import com.gd.reservationservices.presentation.performance.response.CreatePerformanceResponse;
import com.gd.reservationservices.presentation.performance.response.FindPerformanceResponse;
import lombok.RequiredArgsConstructor;
import com.gd.reservationservices.application.performance.PerformanceService;
import com.gd.reservationservices.application.performance.dto.FindPerformance;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.performance.response.FindPerformanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;

@RestController
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
