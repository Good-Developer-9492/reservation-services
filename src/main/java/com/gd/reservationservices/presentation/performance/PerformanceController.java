package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.PerformanceService;
import com.gd.reservationservices.application.performance.dto.CreatePerformanceResult;
import com.gd.reservationservices.application.performance.dto.SearchAllPerformanceResult;
import com.gd.reservationservices.application.performance.dto.SearchPerformanceResult;
import com.gd.reservationservices.common.request.PagingRequest;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.common.response.Paging;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.performance.request.CreatePerformanceRequest;
import com.gd.reservationservices.presentation.performance.request.SearchPerformancesRequest;
import com.gd.reservationservices.presentation.performance.response.CreatePerformanceResponse;
import com.gd.reservationservices.presentation.performance.response.FindPerformanceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
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

    @GetMapping
    public ListResponse<FindPerformanceResponse> searchAllBy(SearchPerformancesRequest request, PagingRequest page) {
        SearchAllPerformanceResult performance = performanceService.searchAllBy(request.toValue(), page.toPageable());

        return new ListResponse.Ok<>(
                performance.performances().stream()
                        .map(FindPerformanceResponse::new)
                        .collect(Collectors.toList()),
                new Paging(
                        performance.totalCount(),
                        performance.currentPage(),
                        performance.perPage(),
                        performance.totalPage()
                )
        );
    }
}
