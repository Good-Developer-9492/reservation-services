package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.dto.SearchReservationListResult;
import com.gd.reservationservices.application.performance.dto.SearchReservationResult;
import com.gd.reservationservices.common.request.PagingRequest;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Place;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.Role;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.presentation.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class BusinessReservationControllerTest extends ControllerTestSupport {
    @DisplayName("특정 공연 예매 목록을 조회한다.")
    @Test
    void searchAllBy() throws Exception {
        //given
        Long performanceId = 1L;
        PagingRequest pagingRequest = new PagingRequest(0, 10);

        LocalDateTime resistTime = LocalDateTime.of(2023, 8, 2, 12, 0, 0);

        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Performance newPerformance = new Performance(
                new Place("공연장 이름", "공연장 주소", 500),
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

        given(reservationService.searchAllBy(any(Long.class), any(PageRequest.class)))
                .willReturn(
                        new SearchReservationListResult(
                                List.of(new Reservation(
                                                new User("user01", "1234", "user01", 20, "user01@mail.com", "01012345678", Role.CUSTOMER),
                                                newPerformance,
                                                new Seat(performanceId, "A", 1),
                                                resistTime),
                                        new Reservation(
                                                new User("user02", "1234", "user02", 20, "user02@mail.com", "01012345678", Role.CUSTOMER),
                                                newPerformance,
                                                new Seat(performanceId, "A", 2),
                                                resistTime)
                                ),
                                2L,
                                0,
                                10,
                                0
                        )

                );

        //when
        //then
        mockMvc.perform(
                        get("/business/performances/{performanceId}/reservations", performanceId, pagingRequest)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("예매 정보를 조회한다.")
    @Test
    void searchBy() throws Exception {
        //given
        Long performanceId = -1L;
        Long reservationId = 1L;

        LocalDateTime resistTime = LocalDateTime.of(2023, 8, 2, 12, 0, 0);

        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        Performance newPerformance = new Performance(
                new Place("공연장 이름", "공연장 주소", 500),
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

        given(reservationService.searchBy(any(Long.class), any(Long.class)))
                .willReturn(new SearchReservationResult(
                        1L,
                        new User("user01", "1234", "user01", 20, "user01@mail.com", "01012345678", Role.CUSTOMER),
                        new Seat(performanceId, "A", 1),
                        LocalDateTime.of(2023, 8, 2, 12, 0, 0)
                ));

        //when
        //then
        mockMvc.perform(
                        get("/business/performances/{performanceId}/reservations/{reservationId}", performanceId, reservationId)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
