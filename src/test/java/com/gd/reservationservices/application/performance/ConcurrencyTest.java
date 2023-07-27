package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.application.performance.dto.ReservationCreateValue;
import com.gd.reservationservices.application.performance.value.LockKey;
import com.gd.reservationservices.domain.performance.LockRepository;
import com.gd.reservationservices.domain.performance.Place;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.infrastructure.performance.PlaceRepository;
import com.gd.reservationservices.infrastructure.performance.ReservationRepository;
import com.gd.reservationservices.infrastructure.performance.SeatRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
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
    private PerformanceService performanceService;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private RedissonClient redissonClient;

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
            } catch (Exception e) {
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
        Boolean lock = lockRepository.lock("99999");
        Assertions.assertTrue(lock);
    }

    @Test
    void redis_lock_key_조합() {
        LockKey lockKey = new LockKey(1L, "A", 10);
        Assertions.assertEquals("1A10", lockKey.combination());
    }

    @Test
    void 분산락_미정용_테스트() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executor.submit(() -> {
                try {
                    // 분산락 미적용 메서드 호출
                    performanceService.decrease("LOCK_TEST");
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Place place = placeRepository.findById(5L).orElseThrow(() -> new IllegalArgumentException("not found"));

        Assertions.assertEquals(0, place.getMaxSeat());
    }

    @Test
    void redisson_pup_sub_lock() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            int finalI = i;
            executor.submit(() -> {
                try {
                    // 분산락 미적용 메서드 호출
                    this.decrease("lockTest", finalI);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Place place = placeRepository.findById(5L).orElseThrow(() -> new IllegalArgumentException("not found"));
        Assertions.assertEquals(0, place.getMaxSeat());
    }


    public void decrease(String key, int count) {
        //key 로 Lock 객체 가져옴
        RLock lock = redissonClient.getLock(key);
        System.out.println("get lock = " + LocalDateTime.now());
        try {
            //획득시도 시간, 락 점유 시간
            boolean available = lock.tryLock(5, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("lock 획득 실패");
                return;
            }
            performanceService.decrease("LOCK_TEST");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("unlock------------------------------------------------------");
            lock.unlock();
        }
    }

}
