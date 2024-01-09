package com.gd.reservationservices.application.performance.dto;

import com.gd.reservationservices.domain.performance.Performance;
import org.springframework.data.domain.Page;

import java.util.List;

public record SearchAllPerformanceResult(
        List<Performance> performances,
        Long totalCount,
        Integer currentPage,
        Integer perPage,
        Integer totalPage
) {
    public SearchAllPerformanceResult(Page<Performance> performances) {
        this(
            performances.getContent(),
            performances.getTotalElements(),
            performances.getPageable().getPageNumber(),
            performances.getPageable().getPageSize(),
            performances.getTotalPages()
        );
    }
}
