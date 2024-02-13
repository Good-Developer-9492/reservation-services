package com.gd.reservationservices.application.point;

import com.gd.reservationservices.IntegrationTestSupport;
import com.gd.reservationservices.domain.point.Point;
import com.gd.reservationservices.domain.point.QPoint;
import com.gd.reservationservices.domain.point.dto.SearchPointGroupBy;
import com.gd.reservationservices.domain.point.repository.PointRepository;
import com.gd.reservationservices.domain.user.Role;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.domain.user.repository.UserRepository;
import com.gd.reservationservices.infrastructure.point.dto.SearchPointResult;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PointServiceTest extends IntegrationTestSupport {
    @Autowired
    PointService pointService;
    @Autowired
    PointRepository pointRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;

    @DisplayName("groupBy")
    @Test
    void groupBy() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

        User user = new User(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345678",
                Role.CUSTOMER
        );
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        pointRepository.save(new Point(userId, Point.Type.ADD, 1000L));
        pointRepository.save(new Point(userId, Point.Type.ADD, 1000L));
        pointRepository.save(new Point(userId, Point.Type.USED, -1000L));

        Tuple tuple = queryFactory.select(
                        QPoint.point.userId,
                        QPoint.point.amount.sum()
                )
                .from(QPoint.point)
                .where(QPoint.point.userId.eq(userId))
                .groupBy(QPoint.point.userId)
                .fetchOne();

        System.out.println("result --- " + tuple.get(QPoint.point.amount.sum()));

        assertThat(tuple.get(QPoint.point.amount.sum())).isEqualTo(1000L);
    }

    @DisplayName("DB Group By를 이용하여 유저의 사용가능한 포인트 정보를 조회한다.")
//    @Test
    void searchUserAvailablePointsUseGroupBy() {
        //given
        User user = new User(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345678",
                Role.CUSTOMER
        );
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        pointRepository.save(new Point(userId, Point.Type.ADD, 1000L));
//        pointRepository.save(new Point(userId, Point.Type.ADD, 1000L));
//        pointRepository.save(new Point(userId, Point.Type.USED, -1000L));

        //when
        Optional<SearchPointGroupBy> groupByUserId = pointRepository.findGroupByUserId(userId);
        SearchPointGroupBy searchPointGroupBy = groupByUserId.get();
//        assertThat(searchPointGroupBy.getAmount()).isEqualTo(1000L);

//        SearchPointResult searchPointResult = pointService.searchGroupBy(userId);
//
//        //then
//        assertThat(searchPointResult.availablePoints()).isEqualTo(1000L);
    }

    @DisplayName("유저의 사용가능한 포인트 정보를 조회한다.")
//    @Test
    void searchUserAvailablePoints() {
        //given
        User user = new User(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345678",
                Role.CUSTOMER
        );
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        pointRepository.save(new Point(userId, Point.Type.ADD, 1000L));
        pointRepository.save(new Point(userId, Point.Type.ADD, 1000L));
        pointRepository.save(new Point(userId, Point.Type.USED, -1000L));
        //when
        SearchPointResult searchPointResult = pointService.searchAllBy(userId);

        //then
        assertThat(searchPointResult.availablePoints()).isEqualTo(1000L);
    }
}