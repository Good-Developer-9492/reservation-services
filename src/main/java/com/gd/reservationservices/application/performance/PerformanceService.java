package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.CreatePerformance;
import com.gd.reservationservices.application.performance.dto.CreatePerformanceCommend;
import com.gd.reservationservices.application.performance.dto.PerformancePlace;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Place;
import com.gd.reservationservices.infrastructure.performance.PerformanceRepository;
import com.gd.reservationservices.infrastructure.performance.PlaceRepository;
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
    private final PlaceRepository placeRepository;

    @Transactional
    public CreatePerformance create(CreatePerformanceCommend createPerformanceCommend) {
        if (performanceRepository.exists(
            createPerformanceCommend.placeId(),
            createPerformanceCommend.startAt(),
            createPerformanceCommend.endAt())
        ) {
            throw new IllegalArgumentException("해당 시간에 공연을 등록할 수 없습니다.");
        }

        Place place = placeRepository.findById(createPerformanceCommend.placeId())
            .orElseThrow(() -> new IllegalArgumentException("공연장 정보가 존재하지 않습니다."));

        Performance newPerformance =
            new Performance(
                place,
                Performance.Category.valueOf(createPerformanceCommend.category()),
                createPerformanceCommend.startAt(),
                createPerformanceCommend.endAt(),
                createPerformanceCommend.startReservationAt(),
                createPerformanceCommend.endReservationAt(),
                createPerformanceCommend.title(),
                createPerformanceCommend.content(),
                createPerformanceCommend.acting(),
                Performance.FilmRating.valueOf(createPerformanceCommend.filmRating())
            );

        performanceRepository.save(newPerformance);

        return new CreatePerformance(
            new PerformancePlace(place.getName(), place.getLocation()),
            newPerformance.getStartAt(),
            newPerformance.getEndAt(),
            newPerformance.getStartReservationAt(),
            newPerformance.getEndReservationAt(),
            Category.valueOf(newPerformance.getCategory().toString()),
            newPerformance.getTitle(),
            newPerformance.getContent(),
            newPerformance.getActing(),
            FilmRating.valueOf(newPerformance.getFilmRating().toString()),
            newPerformance.getCreatedAt(),
            newPerformance.getUpdatedAt()
        );
    }
}
