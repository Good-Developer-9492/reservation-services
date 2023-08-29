package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.application.payment.command.CreatePaymentValue;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.payment.Payment;
import com.gd.reservationservices.domain.payment.repository.CouponRepository;
import com.gd.reservationservices.domain.payment.repository.PaymentRepository;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.repository.PerformanceRepository;
import com.gd.reservationservices.domain.performance.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;
    private final PerformanceRepository performanceRepository;
    private final CouponRepository couponRepository;
    private final OutPaymentService outPaymentService;
//    public SearchPaymentResult search(Long id) {
//        repository.findById(id);
//    }
//
//    public List<SearchPaymentResult> searchAll(Long userId) {
//    }

    public PayResult pay(CreatePaymentValue createPayment) {
        Reservation reservation = reservationRepository
                .findById(createPayment.reservationId())
                .orElseThrow();

        reservation.validUser(createPayment.userID());

        Coupon coupon = couponRepository.findById(createPayment.couponId())
                .orElseThrow();

        coupon.valid();

        Integer discountPrice = new DiscountPriceCalculator(coupon, createPayment.price()).call();

        Payment payment = new Payment(
                reservation.getUser(),
                reservation.getPerformance().getId(),
                coupon.getId(),
                createPayment.price(),
                discountPrice
        );

        payment.validPrice(reservation.getPerformance().getPrice());

        outPaymentService.call();

        paymentRepository.save(payment);

        coupon.use();

        return new PayResult(payment);
    }

}
