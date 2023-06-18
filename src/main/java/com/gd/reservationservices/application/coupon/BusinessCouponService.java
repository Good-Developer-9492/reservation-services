package com.gd.reservationservices.application.coupon;

import com.gd.reservationservices.infrastructure.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessCouponService {
    private final CouponRepository couponRepository;
}