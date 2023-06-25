package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.CouponService;
import com.gd.reservationservices.common.response.EmptyResponse;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.presentation.payment.request.CreateCouponCreateRequest;
import com.gd.reservationservices.presentation.payment.request.UpdateCouponRequest;
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

    //todo 추후에 id가 아닌 쿠폰번호로 조회하기
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<Coupon> getCoupon(@PathVariable Long id) {
        return new SingleResponse.Ok<>(couponService.findById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse updateCoupon(@PathVariable Long id,
                                      @RequestBody UpdateCouponRequest request) {
        couponService.update(request.toValue(id));
        return new EmptyResponse.Ok<>();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse updateCoupon(@PathVariable Long id) {
        couponService.delete(id);
        return new EmptyResponse.Ok<>();
    }

}