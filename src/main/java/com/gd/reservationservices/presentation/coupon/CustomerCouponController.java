package com.gd.reservationservices.presentation.coupon;

import com.gd.reservationservices.application.coupon.CustomerCouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/customer/coupons")
@RequiredArgsConstructor
public class CustomerCouponController {
    private final CustomerCouponService customerCouponService;

}