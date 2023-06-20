package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.CreatePerformance;
import com.gd.reservationservices.application.performance.dto.CreatePerformanceCommand;
import com.gd.reservationservices.application.performance.dto.PerformancePlace;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Place;
import com.gd.reservationservices.infrastructure.performance.PerformanceRepository;
import com.gd.reservationservices.infrastructure.performance.PlaceRepository;
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
    public CreatePerformance create(CreatePerformanceCommand createPerformanceCommand) {
        if (performanceRepository.exists(
            createPerformanceCommand.placeId(),
            createPerformanceCommand.startAt(),
            createPerformanceCommand.endAt())
        ) {
            throw new IllegalArgumentException("해당 시간에 공연을 등록할 수 없습니다.");
        }

        Place place = placeRepository.findById(createPerformanceCommand.placeId())
            .orElseThrow(() -> new IllegalArgumentException("공연장 정보가 존재하지 않습니다."));

        Performance newPerformance = createPerformanceCommand.toEntity(place);

        performanceRepository.save(newPerformance);

        return new CreatePerformance(
            newPerformance,
            new PerformancePlace(place)
        );
    }
}
