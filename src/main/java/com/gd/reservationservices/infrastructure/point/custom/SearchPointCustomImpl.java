package com.gd.reservationservices.infrastructure.point.custom;

import com.gd.reservationservices.domain.point.dto.QSearchPointGroupBy;
import com.gd.reservationservices.domain.point.dto.SearchPointGroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.gd.reservationservices.domain.point.QPoint.*;

@Repository
public class SearchPointCustomImpl implements PointRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public SearchPointCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<SearchPointGroupBy> findGroupByUserId(Long id) {
        return Optional.ofNullable(
            queryFactory
                .select(
                    new QSearchPointGroupBy(
                        point.userId,
                        point.amount.sum().as("amount")
                    )
                )
                .from(point)
                .where(point.userId.eq(id))
                .groupBy(point.userId)
                .fetchOne()
        );
    }
}
