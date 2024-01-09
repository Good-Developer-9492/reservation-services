package com.gd.reservationservices.infrastructure.performance.custom;

import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.infrastructure.performance.dto.SearchPerformance;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.netty.util.internal.StringUtil;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
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

    @Override
    public Page<Performance> findAllWithPlace(SearchPerformance request, Pageable pageable) {
        List<Performance> content =
            queryFactory.select(performance)
                .from(performance)
                .join(place).on(performance.place.id.eq(place.id)).fetchJoin()
                .where(
                    placeIdEq(request.placeId()),
                    categoryEq(request.category()),
                    titleContains(request.title()),
                    between(request.searchStartAt(), request.searchEndAt())
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .orderBy(performance.startAt.desc())
                .fetch();

        JPAQuery<Long> countQuery =
            queryFactory.select(performance.count())
                .from(performance)
                .join(place).on(performance.place.id.eq(place.id)).fetchJoin()
                .where(
                    placeIdEq(request.placeId()),
                    categoryEq(request.category()),
                    titleContains(request.title()),
                    between(request.searchStartAt(), request.searchEndAt())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression placeIdEq(Long id) {
        if (id == null) {
            return null;
        }

        return performance.place.id.eq(id);
    }

    private BooleanExpression categoryEq(String category) {
        return StringUtils.hasText(category)
            ? performance.category.eq(Performance.Category.valueOf(category)) : null;
    }

    private BooleanExpression titleContains(String keyword) {
        return StringUtils.hasText(keyword)
                ? performance.title.contains(keyword) : null;
    }

    private BooleanExpression between(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return null;
        }

        return performance.startAt.between(start, end);
    }
}
