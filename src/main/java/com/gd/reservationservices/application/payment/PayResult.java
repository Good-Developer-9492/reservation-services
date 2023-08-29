package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.domain.payment.Payment;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.infrastructure.user.value.Role;

import java.time.LocalDateTime;

public record PayResult(Long id,
                        User user,
                        Long performanceId,
                        Long couponId,
                        Integer price,
                        Integer discountPrice,
                        LocalDateTime canceledAt
) {
    public PayResult(Payment payment) {
        this(
                payment.getId(),
                payment.getUser(),
                payment.getPerformanceId(),
                payment.getCouponId(),
                payment.getPrice(),
                payment.getDiscountPrice(),
                payment.getCanceledAt()
        );
    }
}
