package com.gd.reservationservices.infrastructure.payment;

import com.gd.reservationservices.domain.payment.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
