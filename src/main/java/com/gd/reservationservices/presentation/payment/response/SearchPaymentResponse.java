package com.gd.reservationservices.presentation.payment.response;

import com.gd.reservationservices.application.payment.dto.SearchPaymentResult;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.user.User;

import java.time.LocalDateTime;

public record SearchPaymentResponse(
        Long id,
        User user,
        Performance performance,
        Coupon coupon,
        Integer price,
        Integer discountPrice,
        LocalDateTime canceledAt
) {

    public SearchPaymentResponse(SearchPaymentResult result) {
        this(
                result.id(),
                result.user(),
                result.performance(),
                result.coupon(),
                result.price(),
                result.discountPrice(),
                result.canceledAt()
        );
    }
}
