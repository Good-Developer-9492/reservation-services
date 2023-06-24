package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.CouponService;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.presentation.payment.request.CreateCouponCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business/coupons")
@RequiredArgsConstructor
public class BusinessCouponController {
    private final CouponService couponService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ListResponse<Coupon> createCoupon(@RequestBody CreateCouponCreateRequest request) {
        List<Coupon> result = couponService.create(request.toValue());
        return new ListResponse.Ok<>(result, null);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<Coupon> getCoupon(@PathVariable Long id) {
        return new SingleResponse.Ok<>(couponService.findById(id));
    }

}