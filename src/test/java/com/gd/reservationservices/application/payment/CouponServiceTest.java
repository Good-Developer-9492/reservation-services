package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.IntegrationTestSupport;
import com.gd.reservationservices.application.payment.dto.*;
import com.gd.reservationservices.common.exception.ErrorCode;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.payment.repository.CouponRepository;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Place;
import com.gd.reservationservices.domain.performance.repository.PerformanceRepository;
import com.gd.reservationservices.domain.performance.repository.PlaceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CouponServiceTest extends IntegrationTestSupport {
    @Autowired
    private CouponService couponService;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private PerformanceRepository performanceRepository;

    @DisplayName("쿠폰 생성한다.")
    @Test
    void create() {
        //given
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        Performance performance = new Performance(
                savedPlace,
                Performance.Category.CLASSIC,
                startTime,
                endTime,
                bookingStartDate,
                bookingEndDate,
                "공연 이름",
                "공연 내용",
                "출연진",
                Performance.FilmRating.ALL,
                50000,
                null,
                null
        );
        Performance savedPerformance = performanceRepository.save(performance);
        Long performanceId = savedPerformance.getId();

        LocalDateTime expiredTime = LocalDateTime.of(2023, 8, 19, 23, 59, 59);
        CreateCouponValue createCouponValue = new CreateCouponValue(
                performanceId,
                Coupon.Type.PERCENT,
                10,
                expiredTime,
                5
        );

        //when
        List<CreateCouponResult> createCouponResults = couponService.create(createCouponValue);

        //then
        assertThat(createCouponResults).hasSize(5)
                .extracting("performanceTitle", "type", "discountValue", "expiredAt")
                .containsExactlyInAnyOrder(
                        tuple("공연 이름", "PERCENT", 10, expiredTime),
                        tuple("공연 이름", "PERCENT", 10, expiredTime),
                        tuple("공연 이름", "PERCENT", 10, expiredTime),
                        tuple("공연 이름", "PERCENT", 10, expiredTime),
                        tuple("공연 이름", "PERCENT", 10, expiredTime)
                );
    }

    @DisplayName("쿠폰 할인 가격이 공연 예매 가격보다 클 경우 예외를 발생한다.")
    @Test
    void createOverDiscountPrice() {
        //given
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        Performance performance = new Performance(
                savedPlace,
                Performance.Category.CLASSIC,
                startTime,
                endTime,
                bookingStartDate,
                bookingEndDate,
                "공연 이름",
                "공연 내용",
                "출연진",
                Performance.FilmRating.ALL,
                50000,
                null,
                null
        );
        Performance savedPerformance = performanceRepository.save(performance);
        Long performanceId = savedPerformance.getId();

        LocalDateTime expiredTime = LocalDateTime.of(2023, 8, 19, 23, 59, 59);
        CreateCouponValue createCouponValue = new CreateCouponValue(
                performanceId,
                Coupon.Type.WON,
                50001,
                expiredTime,
                10
        );

        //when
        //then
        assertThatThrownBy(() -> couponService.create(createCouponValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.valueOf(ErrorCode.COUPON_DISCOUNT_NOT_AVAILABLE_EXCEED_PERFORMANCE_PRICE));
    }

    @DisplayName("쿠폰 할인율이 100%를 넘을 경우 예외를 발생한다.")
    @Test
    void createOverDiscountPercent() {
        //given
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        Performance performance = new Performance(
                savedPlace,
                Performance.Category.CLASSIC,
                startTime,
                endTime,
                bookingStartDate,
                bookingEndDate,
                "공연 이름",
                "공연 내용",
                "출연진",
                Performance.FilmRating.ALL,
                50000,
                null,
                null
        );
        Performance savedPerformance = performanceRepository.save(performance);
        Long performanceId = savedPerformance.getId();

        LocalDateTime expiredTime = LocalDateTime.of(2023, 8, 19, 23, 59, 59);
        CreateCouponValue createCouponValue = new CreateCouponValue(
                performanceId,
                Coupon.Type.PERCENT,
                101,
                expiredTime,
                10
        );

        //when
        //then
        assertThatThrownBy(() -> couponService.create(createCouponValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.valueOf(ErrorCode.COUPON_DISCOUNT_NOT_AVAILABLE_EXCEED_PERFORMANCE_PRICE));
    }


    @DisplayName("")
    @Test
    void update() {
        //given
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        Performance performance = new Performance(
                savedPlace,
                Performance.Category.CLASSIC,
                startTime,
                endTime,
                bookingStartDate,
                bookingEndDate,
                "공연 이름",
                "공연 내용",
                "출연진",
                Performance.FilmRating.ALL,
                50000,
                null,
                null
        );
        Performance savedPerformance = performanceRepository.save(performance);
        Long performanceId = savedPerformance.getId();
        LocalDateTime expiredTime = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Coupon newCoupon =
                new Coupon(savedPerformance, "AAA1234-1234-1234", Coupon.Type.PERCENT, 10, expiredTime);
        Coupon savedCoupon = couponRepository.save(newCoupon);
        Long couponId = savedCoupon.getId();

        UpdateCouponValue updateCouponValue =
                new UpdateCouponValue(couponId, performanceId, Coupon.Type.WON, 10000, expiredTime);

        //when
        UpdateCouponResult result = couponService.update(updateCouponValue);

        //then
        assertThat(result)
                .extracting("performanceTitle", "serialNumber", "type", "discountValue")
                .contains("공연 이름", "AAA1234-1234-1234", "WON", 10000);
    }

    @DisplayName("")
    @Test
    void use() {
        //given
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        Performance performance = new Performance(
                savedPlace,
                Performance.Category.CLASSIC,
                startTime,
                endTime,
                bookingStartDate,
                bookingEndDate,
                "공연 이름",
                "공연 내용",
                "출연진",
                Performance.FilmRating.ALL,
                50000,
                null,
                null
        );
        Performance savedPerformance = performanceRepository.save(performance);
        Long performanceId = savedPerformance.getId();
        LocalDateTime expiredTime = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Coupon newCoupon =
                new Coupon(savedPerformance, "AAA1234-1234-1234", Coupon.Type.PERCENT, 10, expiredTime);
        Coupon savedCoupon = couponRepository.save(newCoupon);

        Long couponId = savedCoupon.getId();
        LocalDateTime useTIme = LocalDateTime.of(2023, 8, 12, 21, 20, 0);

        //when
        UseCouponResult result = couponService.use(couponId, useTIme);

        //then
        assertThat(result)
                .extracting("serialNumber", "type", "discountValue", "usedAt", "expiredAt")
                .contains("AAA1234-1234-1234", "PERCENT", 10, useTIme, expiredTime);
    }
}
