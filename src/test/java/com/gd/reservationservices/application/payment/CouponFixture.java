package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.application.performance.PerformanceFixture;
import com.gd.reservationservices.domain.payment.Coupon;

import java.time.LocalDateTime;

public class CouponFixture{
    static Coupon create10Percent(){
        return new Coupon(
                PerformanceFixture.create(),
                Coupon.Type.PERCENT,
                10,
                LocalDateTime.now().plusDays(5)
        );
    }

    static Coupon create1000Won(){
        return new Coupon(
                PerformanceFixture.create(),
                Coupon.Type.WON,
                1000,
                LocalDateTime.now().plusDays(5)
        );
    }

}
