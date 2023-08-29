package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.domain.payment.Coupon;

public class DiscountPriceCalculator {
    Coupon coupon;
    Integer price;

    public Integer call() {
        if (coupon == null) {
            return 0;
        }

        if (this.coupon.getType().equals(Coupon.Type.PERCENT)) {
            return (int) (this.price * (this.coupon.getValue() * 0.01));
        }

        return this.coupon.getValue();
    }

    public DiscountPriceCalculator(Coupon coupon, Integer price) {
        this.coupon = coupon;
        this.price = price;
    }
}
