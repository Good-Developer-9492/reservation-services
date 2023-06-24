package com.gd.reservationservices.infrastructure.payment.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

public class CouponRepositoryCustomImpl implements CouponRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public CouponRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }
}