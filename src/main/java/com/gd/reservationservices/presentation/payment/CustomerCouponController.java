package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/coupons")
@RequiredArgsConstructor
public class CustomerCouponController {
    private final CouponService couponService;
}