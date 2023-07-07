package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.ReservationCreateValue;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.infrastructure.performance.ReservationRepository;
import com.gd.reservationservices.infrastructure.performance.SeatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ReservationServiceTest {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private SeatRepository seatRepository;

    @Test
    public void 좌석_동시_접근() throws InterruptedException {
        ReservationCreateValue addReservationOfUser2 =
                new ReservationCreateValue(2L, "A", 2);
        ReservationCreateValue addReservationOfUser3 =
                new ReservationCreateValue(3L, "A", 2);
        ReservationCreateValue addReservationOfUser4 =
                new ReservationCreateValue(4L, "A", 2);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.submit(() -> {
            try {
                reservationService.createReservation(2L, addReservationOfUser2);
                System.out.println("====> create reservation first user");
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        executor.submit(() -> {
            try {
                reservationService.createReservation(2L, addReservationOfUser3);
                System.out.println("====> create reservation second user");
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        executor.submit(() -> {
            try {
                reservationService.createReservation(2L, addReservationOfUser4);
                System.out.println("====> create reservation third user");
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        executor.shutdown();

        if (executor.awaitTermination(20, TimeUnit.SECONDS)) {
            System.out.println(LocalTime.now() + " All jobs are terminated");
        } else {
            System.out.println(LocalTime.now() + " some jobs are not terminated");
            executor.shutdownNow();
        }

        Seat seat = seatRepository
                .findByPerformanceIdAndLocationAndNumber(2L, "A", 2)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        int i = reservationRepository.countBySeat(seat);
        System.out.println("========> reservation count is = " + i);
        Assertions.assertEquals(1, i);
    }

    @Test
    void test() {

    }

}
