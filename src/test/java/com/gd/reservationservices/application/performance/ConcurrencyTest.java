package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.ReservationCreateValue;
import com.gd.reservationservices.application.performance.value.LockKey;
import com.gd.reservationservices.application.user.UserService;
import com.gd.reservationservices.domain.performance.LockRepository;
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
class ConcurrencyTest {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private LockRepository lockRepository;
    @Autowired
    private UserService userService;

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
                reservationService.createReservation(1L, addReservationOfUser2);
                System.out.println("====> create reservation first user");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        executor.submit(() -> {
            try {
                reservationService.createReservation(1L, addReservationOfUser3);
                System.out.println("====> create reservation second user");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        executor.submit(() -> {
            try {
                reservationService.createReservation(1L, addReservationOfUser4);
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
    void redis_lock_test() {
        Boolean lock = lockRepository.lock("9999");
        Assertions.assertTrue(lock);
    }

    @Test
    void jpa_persist_cache_test() {
        userService.jpa_persist_test();
    }

    @Test
    void jpa_persist_merge_test() {
        userService.jpa_persist_merge_test();
    }

    @Test
    void redis_lock_key_조합() {
        LockKey lockKey = new LockKey(1L, "A", 10);
        Assertions.assertEquals("1A10", lockKey.combination());
    }
}
