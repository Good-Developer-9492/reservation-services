package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.CouponService;
import com.gd.reservationservices.common.response.EmptyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/coupons")
@RequiredArgsConstructor
public class CustomerCouponController {
    private final CouponService couponService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse useCoupon(@PathVariable Long id) {
        couponService.use(id);
        return new EmptyResponse.Ok<>();
    }
}