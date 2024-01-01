package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.dto.CreateReservationResult;
import com.gd.reservationservices.application.performance.dto.CreateReservationValue;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.performance.Place;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.domain.user.Role;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.presentation.ControllerTestSupport;
import com.gd.reservationservices.presentation.performance.request.ReservationCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class CustomerReservationControllerTest extends ControllerTestSupport {
    @DisplayName("")
    @Test
    void create() throws Exception {
        //given
        Long performanceId = 1L;
        ReservationCreateRequest request = new ReservationCreateRequest(1L, "A", 1);

        LocalDateTime reservedTime = LocalDateTime.of(2023, 8, 2, 13, 0, 0);

        given(reservationService.create(any(Long.class),any(CreateReservationValue.class)))
                        .willReturn(new CreateReservationResult(
                                1L,
                                new User("user01", "1234", "name", 20, "user01@test.com", "01012345678", Role.CUSTOMER),
                                new Performance(
                                        new Place("공연장 이름", "공연장 주소", 500),
                                        Performance.Category.SPORT,
                                        LocalDateTime.of(2023, 8, 20, 14, 0, 0),
                                        LocalDateTime.of(2023, 8, 20, 16, 30, 0),
                                        LocalDateTime.of(2023, 8, 1, 0, 0, 0),
                                        LocalDateTime.of(2023, 8, 19, 23, 59, 59),
                                        "공연 이름",
                                        "공연 내용",
                                        "출연진",
                                        Performance.FilmRating.TWELVE,
                                        50000,
                                        null,
                                        null
                                ),
                                new Seat(1L, "A", 1),
                                reservedTime
                        ));

        //when
        //then
        mockMvc.perform(
                        MockMvcRequestBuilders.post(
                                        "/customer/performances/{performanceId}/reservations",
                                        performanceId
                                )
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
