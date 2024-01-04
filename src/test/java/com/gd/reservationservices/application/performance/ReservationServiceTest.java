package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.IntegrationTestSupport;
import com.gd.reservationservices.application.performance.dto.CreateReservationResult;
import com.gd.reservationservices.application.performance.dto.CreateReservationValue;
import com.gd.reservationservices.application.performance.dto.SearchReservationListResult;
import com.gd.reservationservices.application.performance.dto.SearchReservationResult;
import com.gd.reservationservices.common.exception.ErrorCode;
import com.gd.reservationservices.common.request.PagingRequest;
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

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationServiceTest extends IntegrationTestSupport {
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlaceRepository placeRepository;

    @DisplayName("공연 좌석예매 한다.")
    @Test
    void create() {
        //given
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Performance newPerformance = new Performance(
                savedPlace,
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
        );
        Performance savedPerformance = performanceRepository.save(newPerformance);
        Long performanceId = savedPerformance.getId();

        Seat newSeat = new Seat(performanceId, "A", 1);
        seatRepository.save(newSeat);

        User user = new User(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345678",
                Role.CUSTOMER
        );
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        CreateReservationValue request =
                new CreateReservationValue(userId, "A", 1);

        //when
        CreateReservationResult createReservationResult =
                reservationService.create(performanceId, request);

        //then
        assertThat(createReservationResult.user())
                .extracting("userId", "userPw", "name", "age", "email", "phone", "role")
                .contains("user01", "user1234", "user01", 22, "user01@test.com", "01012345678", Role.CUSTOMER);

        assertThat(createReservationResult.seat())
                .extracting("performanceId", "location", "number", "isReserved")
                .contains(performanceId, "A", 1, true);

        assertThat(createReservationResult.performance())
                .extracting("category", "startAt", "startReservationAt", "endReservationAt", "title", "content", "acting", "filmRating")
                .contains(Performance.Category.SPORT, startTime, bookingStartDate, bookingEndDate, "공연 이름", "공연 내용", "출연진", Performance.FilmRating.TWELVE);
    }

    @DisplayName("")
    @Test
    void alreadyDuplicateRegistration() {
        //given
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Performance newPerformance = new Performance(
                savedPlace,
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
        );
        Performance savedPerformance = performanceRepository.save(newPerformance);
        Long performanceId = savedPerformance.getId();

        Seat newSeat = new Seat(performanceId, "A", 1, true);
        Seat savedSeat = seatRepository.save(newSeat);

        User user = new User(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345678",
                Role.CUSTOMER
        );
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        Reservation newReservation = new Reservation(
                savedUser,
                savedPerformance,
                savedSeat,
                LocalDateTime.of(2023, 8, 12, 12, 20, 0)
        );
        reservationRepository.save(newReservation);

        CreateReservationValue request =
                new CreateReservationValue(userId, "A", 1);

        //when
        //then
        assertThatThrownBy(() -> reservationService.create(performanceId, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.valueOf(ErrorCode.ALREADY_RESERVED_SEAT));
    }

    @DisplayName("공연에 속한 예약 목록을 조회한다.")
    @Test
    void searchAllBy() {
        //given
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        LocalDateTime resistTime = LocalDateTime.of(2023, 8, 2, 12, 0, 0);
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);
        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Performance newPerformance = new Performance(
                savedPlace,
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
        );
        Performance savedPerformance = performanceRepository.save(newPerformance);

        User newUser1 = new User("user01", "1234", "user01", 20, "user01@mail.com", "01012345678", Role.CUSTOMER);
        User savedUser1 = userRepository.save(newUser1);
        Seat newSeat1 = new Seat(savedPerformance.getId(), "A", 1);
        Seat savedSeat1 = seatRepository.save(newSeat1);
        Reservation reservation1 = new Reservation(savedUser1, newPerformance, savedSeat1, resistTime);

        User newUser2 = new User("user02", "1234", "user02", 20, "user02@mail.com", "01012345678", Role.CUSTOMER);
        User savedUser2 = userRepository.save(newUser2);
        Seat newSeat2 = new Seat(savedPerformance.getId(), "A", 2);
        Seat savedSeat2 = seatRepository.save(newSeat2);
        Reservation reservation2 = new Reservation(savedUser2, newPerformance, savedSeat2, resistTime);

        reservationRepository.saveAll(List.of(reservation1, reservation2));

        PagingRequest pagingRequest = new PagingRequest(0, 10);
        //when
        SearchReservationListResult result = reservationService.searchAllBy(savedPerformance.getId(), pagingRequest.toPageable());

        //then
        assertThat(result)
                .extracting("totalCount", "currentPage", "perPage", "totalPage")
                .contains(2L, 0, 10, 1);
        assertThat(result.reservations()).hasSize(2);
    }

    @DisplayName("예매 정보를 조회한다.")
    @Test
    void searchBy() {
        //given
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);
        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Performance newPerformance = new Performance(
                savedPlace,
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
        );
        Performance savedPerformance = performanceRepository.save(newPerformance);
        Long performanceId = savedPerformance.getId();

        User newUser = new User("user01", "1234", "이름", 20, "user01@mail.com", "01012345678", Role.CUSTOMER);
        User savedUser = userRepository.save(newUser);
        Seat newSeat = new Seat(savedPerformance.getId(), "A", 1);
        Seat savedSeat = seatRepository.save(newSeat);

        LocalDateTime resistTime = LocalDateTime.of(2023, 8, 2, 12, 0, 0);
        Reservation newReservation = new Reservation(savedUser, savedPerformance, savedSeat, resistTime);
        Reservation savedReservation = reservationRepository.save(newReservation);
        Long reservationId = savedReservation.getId();

        //when
        SearchReservationResult searchReservationResult = reservationService.searchBy(savedPerformance.getId(), reservationId);

        //then
        assertThat(searchReservationResult.user())
                .extracting("userId", "name", "phone")
                .contains("user01", "이름", "01012345678");
        assertThat(searchReservationResult.seat())
                .extracting("performanceId", "location", "number")
                .contains(performanceId, "A", 1);
    }

    @DisplayName("조회하려는 예약 정보와 공연 번호가 다른경우 예외를 발생한다.")
    @Test
    void searchByDifferentPerformanceId() {
        //given
        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);
        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Performance newPerformance = new Performance(
                savedPlace,
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
        );
        Performance savedPerformance = performanceRepository.save(newPerformance);
        Long performanceId = savedPerformance.getId();

        User newUser = new User("user01", "1234", "이름", 20, "user01@mail.com", "01012345678", Role.CUSTOMER);
        User savedUser = userRepository.save(newUser);
        Seat newSeat = new Seat(savedPerformance.getId(), "A", 1);
        Seat savedSeat = seatRepository.save(newSeat);

        LocalDateTime resistTime = LocalDateTime.of(2023, 8, 2, 12, 0, 0);
        Reservation newReservation = new Reservation(savedUser, savedPerformance, savedSeat, resistTime);
        Reservation savedReservation = reservationRepository.save(newReservation);
        Long reservationId = savedReservation.getId();

        //when
        //then
        assertThatThrownBy(() -> reservationService.searchBy(Long.MAX_VALUE, reservationId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.valueOf(ErrorCode.RESERVATION_NOT_MATCHED_PERFORMANCE));
    }

//    @Test
    public void 좌석_동시_접근() throws InterruptedException {

        Place newPlace = new Place("공연장 이름", "공연장 주소", 500);
        Place savedPlace = placeRepository.save(newPlace);

        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);
        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Performance newPerformance = new Performance(
                savedPlace,
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
        );
        Performance savedPerformance = performanceRepository.save(newPerformance);
        Long performanceId = savedPerformance.getId();

        Seat newSeat = new Seat(performanceId, "A", 1);
        seatRepository.save(newSeat);

        CreateReservationValue addReservationOfUser2 =
                new CreateReservationValue(1L, "A", 1);
        CreateReservationValue addReservationOfUser3 =
                new CreateReservationValue(2L, "A", 1);
        CreateReservationValue addReservationOfUser4 =
                new CreateReservationValue(3L, "A", 1);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        performanceRepository.flush();

        executor.submit(() -> {
            try {
                reservationService.create(performanceId, addReservationOfUser2);
                System.out.println("====> create reservation first user");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        executor.submit(() -> {
            try {
                reservationService.create(performanceId, addReservationOfUser3);
                System.out.println("====> create reservation second user");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        executor.submit(() -> {
            try {
                reservationService.create(performanceId, addReservationOfUser4);
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

        Seat findSeat = seatRepository.findByPerformanceIdAndLocationAndNumber(performanceId, "A", 2)
                .orElseThrow();

        System.out.println(findSeat.getIsReserved());

        List<Reservation> reservations = reservationRepository.findAll();
        assertThat(reservations).hasSize(1);

    }
}
