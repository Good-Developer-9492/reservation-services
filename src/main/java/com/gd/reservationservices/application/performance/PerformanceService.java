package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.FindPerformance;
import com.gd.reservationservices.application.performance.dto.PerformancePlace;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.infrastructure.performance.PerformanceRepository;
import com.gd.reservationservices.infrastructure.performance.value.Category;
import com.gd.reservationservices.infrastructure.performance.value.FilmRating;
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
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        return new FindPerformance(
            performance.getId(),
            new PerformancePlace(
                    performance.getPlace().getName(),
                    performance.getPlace().getLocation()
            ),
            Category.valueOf(performance.getCategory().toString()),
            performance.getStartAt(),
            performance.getStartReservationAt(),
            performance.getEndReservationAt(),
            performance.getCreatedAt(),
            performance.getUpdatedAt(),
            performance.getTitle(),
            performance.getContent(),
            performance.getActing(),
            FilmRating.valueOf(performance.getFilmRating().toString())
        );
    }
}
