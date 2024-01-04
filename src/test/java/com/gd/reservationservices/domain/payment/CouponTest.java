package com.gd.reservationservices.domain.payment;

import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Place;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class CouponTest {
    @DisplayName("쿠폰 정보를 수정한다.")
    @Test
    void update() {
        //given
        Performance performance = new Performance(
                new Place("공연장 이름", "공연장 주소", 500),
                Performance.Category.CLASSIC,
                LocalDateTime.of(2023, 8, 20, 14, 0, 0),
                LocalDateTime.of(2023, 8, 20, 16, 30, 0),
                LocalDateTime.of(2023, 8, 1, 0, 0, 0),
                LocalDateTime.of(2023, 8, 19, 23, 59, 59),
                "공연 이름",
                "공연 내용",
                "출연진",
                Performance.FilmRating.ALL,
                50000,
                null,
                null
        );
        LocalDateTime expiredTime =
                LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Coupon newCoupon =
                new Coupon(performance, "AAA1234-1234-1234", Coupon.Type.PERCENT, 10, expiredTime);

        LocalDateTime updatedExpiredTime =
                LocalDateTime.of(2023, 8, 18, 23, 59, 59);

        //when
        Coupon updatedCoupon =
                newCoupon.update(performance, Coupon.Type.WON, 10000, updatedExpiredTime);

        //then
        assertThat(updatedCoupon)
                .extracting("serialNumber", "type", "discountValue", "expiredAt")
                .contains("AAA1234-1234-1234", Coupon.Type.WON, 10000, updatedExpiredTime);
    }

    @DisplayName("쿠폰을 사용하면 사용날짜 데이터가 추가된다.")
    @Test
    void use() {
        //given
        Performance performance = new Performance(
                new Place("공연장 이름", "공연장 주소", 500),
                Performance.Category.CLASSIC,
                LocalDateTime.of(2023, 8, 20, 14, 0, 0),
                LocalDateTime.of(2023, 8, 20, 16, 30, 0),
                LocalDateTime.of(2023, 8, 1, 0, 0, 0),
                LocalDateTime.of(2023, 8, 19, 23, 59, 59),
                "공연 이름",
                "공연 내용",
                "출연진",
                Performance.FilmRating.ALL,
                50000,
                null,
                null
        );
        LocalDateTime expiredTime =
                LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Coupon newCoupon =
                new Coupon(performance, "AAA1234-1234-1234", Coupon.Type.PERCENT, 10, expiredTime);

        LocalDateTime useTIme = LocalDateTime.of(2023, 8, 12, 21, 20, 0);

        //when
        Coupon updatedCoupon = newCoupon.use(useTIme);

        //then
        assertThat(updatedCoupon)
                .extracting("serialNumber", "type", "discountValue", "usedAt", "expiredAt")
                .contains("AAA1234-1234-1234", Coupon.Type.PERCENT, 10, useTIme, expiredTime);
    }
}
