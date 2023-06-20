package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.infrastructure.payment.CouponRepository;
import com.gd.reservationservices.infrastructure.performance.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {
    private final CouponRepository couponRepository;
    private final PerformanceRepository performanceRepository;

    @Transactional
    public List<Coupon> create(CouponCommand command) {
        Performance performance = performanceRepository.findById(command.performanceId())
                .orElseThrow(() -> new IllegalArgumentException("공연정보를 찾을 수 없습니다"));

        List<Coupon> coupons = new ArrayList<>();

        for (int i = 0; i < command.amount(); i++) {
            Coupon coupon = new Coupon(
                    performance,
                    command.type(),
                    command.value(),
                    command.expiredAt());
            if (coupon.isOverPrice(command.value())) {
                throw new IllegalArgumentException("할인 금액은 공연가격(비율)을 초과할 수 없습니다");
            }
            coupons.add(coupon);
        }
        return couponRepository.saveAll(coupons);
    }
}
