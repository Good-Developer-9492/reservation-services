package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/business/coupons")
@RequiredArgsConstructor
public class BusinessCouponController {
    private final CouponService couponService;
}