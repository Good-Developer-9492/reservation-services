package com.gd.reservationservices.infrastructure.performance.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;

import static com.gd.reservationservices.domain.performance.QPerformance.performance;

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
                        performance.startAt.between(startAt, endAt),
                        performance.endAt.between(startAt, endAt)
                )
                .fetchFirst();
        return fetchOne != null;
    }
}
