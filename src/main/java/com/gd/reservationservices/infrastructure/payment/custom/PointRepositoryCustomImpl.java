package com.gd.reservationservices.infrastructure.payment.custom;

import com.gd.reservationservices.domain.payment.Point;
import com.gd.reservationservices.domain.payment.QPoint;
import com.gd.reservationservices.infrastructure.payment.custom.dto.PointSum;
import com.gd.reservationservices.infrastructure.payment.custom.dto.QPointSum;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.Optional;

import static com.gd.reservationservices.domain.payment.QPoint.*;

public class PointRepositoryCustomImpl implements PointRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public PointRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<PointSum> findGroupByUserId(Long id) {
        return Optional.ofNullable(
            queryFactory
                .select(
                    new QPointSum(
                        point.userId,
                        point.value.sum()
                    )
                )
                .from(point)
                .where(point.userId.eq(id))
                .groupBy(point.userId)
                .fetchOne()
        );
    }
}
