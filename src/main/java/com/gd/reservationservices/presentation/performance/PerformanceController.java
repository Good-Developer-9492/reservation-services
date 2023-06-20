package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.PerformanceService;
import com.gd.reservationservices.application.performance.dto.FindPerformance;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.performance.response.FindPerformanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/performance")
public class PerformanceController {
    private final PerformanceService performanceService;

    @GetMapping("/business/{id}")
    public SingleResponse<FindPerformanceResponse> find(@PathVariable Long id) {
        FindPerformance findPerformance = performanceService.find(id);

        return new SingleResponse.Ok<>(
                new FindPerformanceResponse(
                        findPerformance.id(),
                        findPerformance.place(),
                        findPerformance.category(),
                        findPerformance.startAt(),
                        findPerformance.startReservationAt(),
                        findPerformance.endReservationAt(),
                        findPerformance.createdAt(),
                        findPerformance.updatedAt(),
                        findPerformance.title(),
                        findPerformance.content(),
                        findPerformance.acting(),
                        findPerformance.filmRating()
                )
        );
    }
}
