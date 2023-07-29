package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.common.exception.ErrorCode;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.infrastructure.payment.CouponRepository;
import com.gd.reservationservices.infrastructure.performance.PerformanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponService {
    private final CouponRepository couponRepository;
    private final PerformanceRepository performanceRepository;

    @Transactional
    public List<CreateCouponResult> create(CreateCouponValue command) {
        Performance performance = performanceRepository.findById(command.performanceId())
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.PERFORMANCE_NOT_FOUND.name()));

        List<Coupon> coupons = new ArrayList<>();

        for (int i = 0; i < command.amount(); i++) {
            Coupon coupon = new Coupon(
                    performance,
                    command.type(),
                    command.value(),
                    command.expiredAt());
            if (coupon.isOverPrice(command.value())) {
                throw new IllegalArgumentException(ErrorCode.COUPON_DISCOUNT_NOT_AVAILABLE_EXCEED_PERFORMANCE_PRICE.name());
            }
            coupons.add(coupon);
        }
        return couponRepository.saveAll(coupons)
                .stream().map(CreateCouponResult::new)
                .collect(Collectors.toList());

    }

    public SearchCouponResult search(Long id) {
        Coupon result = couponRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(ErrorCode.COUPON_NOT_FOUND.name()));
        return new SearchCouponResult(result);

    }

    @Transactional
    public void update(UpdateCouponValue value) {
        Coupon coupon = couponRepository.findById(value.id()).orElseThrow(() ->
                new IllegalArgumentException(ErrorCode.COUPON_NOT_FOUND.name()));

        Performance performance = performanceRepository.findById(value.performanceId())
                .orElseThrow(() -> new IllegalArgumentException(ErrorCode.PERFORMANCE_NOT_FOUND.name()));

        couponRepository.save(coupon.update(performance, value.type(), value.value(), value.expiredAt()));
    }

    @Transactional
    public void use(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(ErrorCode.COUPON_NOT_FOUND.name()));

        couponRepository.save(coupon.use());
    }

    @Transactional
    public void delete(Long id) {
        Coupon coupon = couponRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(ErrorCode.COUPON_NOT_FOUND.name()));
        couponRepository.save(coupon.delete());
    }
}
