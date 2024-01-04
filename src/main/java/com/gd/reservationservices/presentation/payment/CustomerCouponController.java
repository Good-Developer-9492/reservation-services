package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.CouponService;
import com.gd.reservationservices.application.payment.dto.UseCouponResult;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.payment.response.UseCouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/customer/coupons")
@RequiredArgsConstructor
public class CustomerCouponController {
    private final CouponService couponService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<UseCouponResponse> useCoupon(@PathVariable Long id) {
        UseCouponResult result = couponService.use(id, LocalDateTime.now());

        return new SingleResponse.Ok<>(
                new UseCouponResponse(result)
        );
    }
}
