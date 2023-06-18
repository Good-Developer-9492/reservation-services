package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.CouponCommand;
import com.gd.reservationservices.application.payment.CouponService;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.presentation.payment.request.CreateCouponCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("business/coupons")
@RequiredArgsConstructor
public class BusinessCouponController {
    private final CouponService couponService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ListResponse<Coupon> createCoupon(@RequestBody CreateCouponCreateRequest request) {
        List<Coupon> result = couponService.create(new CouponCommand(
                request.performanceId(),
                request.type(),
                request.value(),
                request.expiredAt(),
                request.amount()
        ));
        return new ListResponse.Ok<>(result, null);
    }
}