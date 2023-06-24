package com.gd.reservationservices.infrastructure.performance.custom;

import com.gd.reservationservices.domain.performance.Performance;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.gd.reservationservices.domain.performance.QPerformance.performance;
import static com.gd.reservationservices.domain.performance.QPlace.place;

public class PerformanceRepositoryCustomImpl implements PerformanceRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public PerformanceRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public boolean exists(Long placeId, LocalDateTime startAt, LocalDateTime endAt) {
        Integer fetchOne = queryFactory
            .selectOne()
            .from(performance)
            .where(
                performance.place.id.eq(placeId),
                performance.startAt.between(startAt, endAt)
                    .or(performance.endAt.between(startAt, endAt))
            )
            .fetchFirst();
        return fetchOne != null;
    }

    @Override
    public Optional<Performance> findPerformanceAndPlace(Long id) {
        return Optional.ofNullable(queryFactory
            .selectFrom(performance)
            .join(performance.place, place).fetchJoin()
            .where(
                performance.id.eq(id)
            )
            .fetchOne());
    }
}
