package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.dto.CreatePerformanceResult;
import com.gd.reservationservices.application.performance.dto.CreatePerformanceValue;
import com.gd.reservationservices.application.performance.dto.PerformancePlace;
import com.gd.reservationservices.application.performance.dto.SearchPerformanceResult;
import com.gd.reservationservices.presentation.ControllerTestSupport;
import com.gd.reservationservices.presentation.performance.request.CreatePerformanceRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class PerformanceControllerTest extends ControllerTestSupport {
    @DisplayName("공연 정보를 등록한다.")
    @Test
    void create() throws Exception {
        //given
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 8, 20, 16, 30, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);
        CreatePerformanceRequest request = new CreatePerformanceRequest(
                1L,
                "CLASSIC",
                startTime,
                endTime,
                bookingStartDate,
                bookingEndDate,
                "공연 이름",
                "공연 내용",
                "출연진",
                "ALL",
                50000,
                List.of(
                        new CreatePerformanceRequest.SeatRequest("A", 100),
                        new CreatePerformanceRequest.SeatRequest("B", 100),
                        new CreatePerformanceRequest.SeatRequest("C", 100)
                )
        );

        LocalDateTime createdAt = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        given(performanceService.create(any(CreatePerformanceValue.class)))
                .willReturn(new CreatePerformanceResult(
                        new PerformancePlace("공연장 이름", "서울시 강남구 역삼동"),
                        startTime,
                        endTime,
                        bookingStartDate,
                        bookingEndDate,
                        "CLASSIC",
                        "공연 이름",
                        "공연 내용",
                        "출연진",
                        "ALL",
                        createdAt,
                        updatedAt
                ));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/performance")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("공연 정보를 조회 한다.")
    @Test
    void searchBy() throws Exception{
        //given
        Long id = 1L;
        LocalDateTime startTime = LocalDateTime.of(2023, 8, 20, 14, 0, 0);

        LocalDateTime bookingStartDate = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime bookingEndDate = LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        LocalDateTime createdAt = LocalDateTime.of(2023, 8, 1, 0, 0, 0);
        LocalDateTime updatedAt = LocalDateTime.of(2023, 8, 1, 0, 0, 0);

        given(performanceService.searchBy(any(Long.class)))
                .willReturn(new SearchPerformanceResult(
                        id,
                        new PerformancePlace("공연장", "공연장 주소"),
                        "CLASSIC",
                        startTime,
                        bookingStartDate,
                        bookingEndDate,
                        createdAt,
                        updatedAt,
                        "제목",
                        "내용",
                        "출연진",
                        "ALL"
                ));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/performance/business/{id}", id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
