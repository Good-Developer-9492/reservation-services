package com.gd.reservationservices.application.payment;

import com.gd.reservationservices.application.payment.command.CreatePaymentValue;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.domain.payment.repository.CouponRepository;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Place;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.performance.repository.PerformanceRepository;
import com.gd.reservationservices.domain.performance.repository.PlaceRepository;
import com.gd.reservationservices.domain.performance.repository.ReservationRepository;
import com.gd.reservationservices.domain.performance.repository.SeatRepository;
import com.gd.reservationservices.domain.user.Role;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private CouponRepository couponRepository;

    @DisplayName("쿠폰없이 결제")
    @Test
    public void pay() {
        User user = userRepository.save(
                new User(
                        "Test_1234",
                        "1234",
                        "test_Name",
                        20,
                        "test@email.com",
                        "010-0000-0000",
                        Role.CUSTOMER
                )
        );

        Place place = placeRepository.save(
                new Place("공연장 이름", "공연장 주소", 500));

        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);
        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Performance performance = performanceRepository.save(
                new Performance(
                        place,
                        Performance.Category.SPORT,
                        startTime,
                        endTime,
                        bookingStartDate,
                        bookingEndDate,
                        "공연 이름",
                        "공연 내용",
                        "출연진",
                        Performance.FilmRating.TWELVE,
                        50000,
                        null,
                        null
                )
        );

        Seat seat = seatRepository.save(new Seat(performance.getId(), "A", 1));

        reservationRepository.save(
                new Reservation(
                        user,
                        performance,
                        seat,
                        LocalDateTime.now()
                )
        );

        Coupon coupon = couponRepository.save(
            new Coupon(
                performance,
                Coupon.Type.PERCENT,
                10,
                LocalDateTime.of(2023, 8, 19, 23, 59, 59)
            ));
        Long couponId = coupon.getId();


        PayResult result = paymentService.pay(
            new CreatePaymentValue(
                user.getId(),
                1L,
                couponId,
                50000
            )
        );

    }
}