package com.gd.reservationservices.infrastructure.coupon;

import com.gd.reservationservices.domain.payment.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}