package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.application.payment.command.CreatePaymentValue;
import com.gd.reservationservices.application.payment.dto.SearchPaymentResult;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.payment.Payment;
import com.gd.reservationservices.domain.payment.repository.CouponRepository;
import com.gd.reservationservices.domain.payment.repository.PaymentRepository;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.repository.PerformanceRepository;
import com.gd.reservationservices.domain.performance.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final ReservationRepository reservationRepository;
    private final PerformanceRepository performanceRepository;
    private final CouponRepository couponRepository;
    private final OutPaymentService outPaymentService;
    public SearchPaymentResult search(Long id) {
        repository.findById(id);
    }

    public List<SearchPaymentResult> searchAll(Long userId) {
    }

    public void pay(CreatePaymentValue value) {
        Reservation reservation = reservationRepository.findById(value.reservationId()).orElseThrow();
        reservation.validUser(value.userID());

        //낸 값과 퍼포먼스의 값이 다르면 return

        Coupon coupon = null;
        if(value.couponId() != null) {
            coupon = couponRepository.findById(value.couponId())
                    .orElseThrow();
            int discountPrice = coupon.calDiscountPrice()
            //할인율을 적용한다
        }

        //금액 비교

        //payevent
        outPaymentService.call();

    }

    public void refund(Long id, CreatePaymentValue value) {
        //쿠폰 환불
        //좌석 되돌리기
    }
}
