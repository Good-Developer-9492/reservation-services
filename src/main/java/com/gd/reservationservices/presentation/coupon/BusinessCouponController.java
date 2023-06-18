package com.gd.reservationservices.presentation.coupon;

import com.gd.reservationservices.application.coupon.BusinessCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/business/coupons")
@RequiredArgsConstructor
public class BusinessCouponController {
    private final BusinessCouponService businessCouponService;
}