package com.gd.reservationservices.infrastructure.performance.custom;

import com.gd.reservationservices.domain.performance.Performance;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.Optional;

import static com.gd.reservationservices.domain.performance.QPerformance.performance;
import static com.gd.reservationservices.domain.performance.QPlace.place;

public class PerformanceRepositoryCustomImpl implements PerformanceRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public PerformanceRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
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
