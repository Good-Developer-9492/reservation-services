package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.infrastructure.payment.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
}
