package com.gd.reservationservices.domain.performance;

import com.gd.reservationservices.application.performance.dto.CreatePerformanceValue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class PerformanceSeatGroupsTest {

    @DisplayName("등록 좌석 수의 합이 공연장 최대 좌석 수를 넘을수 없다.")
    @Test
    void seatRegistration() {
        //given
        int MAXIMUM_SEATING_CAPACITY_IN_THE_VENUE_1 = 15000;
        int MAXIMUM_SEATING_CAPACITY_IN_THE_VENUE_2 = 14999;
        CreatePerformanceValue createPerformanceValue = new CreatePerformanceValue(
                1L,
                "MUSICAL",
                LocalDateTime.of(2023, 1, 20, 12, 0),
                LocalDateTime.of(2023, 1, 20, 13, 30),
                LocalDateTime.of(2023, 1, 1, 0, 0),
                LocalDateTime.of(2023, 1, 19, 23, 59),
                "공연 등록",
                "공앤 내용",
                "출연진",
                "ALL",
                50_000,
                List.of(
                        new CreatePerformanceValue.SeatValue("A", 5000),
                        new CreatePerformanceValue.SeatValue("B", 5000),
                        new CreatePerformanceValue.SeatValue("C", 5000)
                )
        );

        PerformanceSeatGroups performanceSeats =
                new PerformanceSeatGroups(
                        createPerformanceValue.seats().stream()
                                .map(s -> new PerformanceSeatGroups.SeatInfo(s.location(), s.seatCount()))
                                .collect(Collectors.toList())
                );


        //when
        boolean seatRegistrationIsAvailable =
                performanceSeats.seatRegistrationAvailable(MAXIMUM_SEATING_CAPACITY_IN_THE_VENUE_1);

        boolean seatRegistrationIsNotPossible =
                performanceSeats.seatRegistrationAvailable(MAXIMUM_SEATING_CAPACITY_IN_THE_VENUE_2);

        //then
        assertThat(seatRegistrationIsAvailable).isTrue();
        assertThat(seatRegistrationIsNotPossible).isFalse();
    }

    @Test
    void date_transform() {
        LocalDateTime dt = LocalDateTime.now();
        java.sql.Date date = new java.sql.Date(Timestamp.valueOf(LocalDateTime.now()).getTime());
        System.out.println("date = " + date);
    }
}
