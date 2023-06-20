package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.FindPerformance;
import com.gd.reservationservices.application.performance.dto.PerformancePlace;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.infrastructure.performance.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public FindPerformance find(Long id) {
        Performance performance = performanceRepository.findPerformanceAndPlace(id)
            .orElseThrow(() -> new IllegalArgumentException("공연 정보가 존재하지 않습니다."));

        return new FindPerformance(
            performance,
            new PerformancePlace(performance.getPlace())
        );
    }
}
