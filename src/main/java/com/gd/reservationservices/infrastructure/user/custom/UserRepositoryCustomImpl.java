package com.gd.reservationservices.infrastructure.user.custom;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.gd.reservationservices.domain.user.QUser.user;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Boolean exists(String userId) {
        Integer fetchOne = queryFactory
            .selectOne()
            .from(user)
            .where(
                user.userId.eq(userId)
            )
            .fetchFirst();
        return fetchOne != null;
    }
}
