package com.gd.reservationservices.application.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DiscountPriceCalculatorTest {

    @Test
    void callPercentCoupon() {
        Integer sut = new DiscountPriceCalculator(
                CouponFixture.create10Percent(),
                10000).call();

        Assertions.assertThat(sut).isEqualTo(1000);
    }

    @Test
    void callWonCoupon() {
        Integer sut = new DiscountPriceCalculator(
                CouponFixture.create10Percent(),
                10000).call();

        Assertions.assertThat(sut).isEqualTo(1000);
    }
}