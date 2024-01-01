package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.dto.CreateCouponResult;
import com.gd.reservationservices.application.payment.dto.CreateCouponValue;
import com.gd.reservationservices.application.payment.dto.UpdateCouponResult;
import com.gd.reservationservices.application.payment.dto.UpdateCouponValue;
import com.gd.reservationservices.domain.payment.Coupon;
import com.gd.reservationservices.presentation.ControllerTestSupport;
import com.gd.reservationservices.presentation.payment.request.CreateCouponRequest;
import com.gd.reservationservices.presentation.payment.request.UpdateCouponRequest;
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

class BusinessCouponControllerTest extends ControllerTestSupport {
    @DisplayName("쿠폰을 등록한다.")
    @Test
    void create() throws Exception {
        //given
        LocalDateTime expiredTime =
                LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        CreateCouponRequest createCouponRequest = new CreateCouponRequest(
                1L,
                Coupon.Type.PERCENT,
                20,
                expiredTime,
                5
        );

        given(couponService.create(any(CreateCouponValue.class)))
                .willReturn(
                        List.of(
                                new CreateCouponResult(1L, "공연 제목", "AAA1234-1234", "PERCENT", 20, expiredTime),
                                new CreateCouponResult(2L, "공연 제목", "AAA1234-1235", "PERCENT", 20, expiredTime),
                                new CreateCouponResult(3L, "공연 제목", "AAA1234-1236", "PERCENT", 20, expiredTime),
                                new CreateCouponResult(4L, "공연 제목", "AAA1234-1237", "PERCENT", 20, expiredTime),
                                new CreateCouponResult(5L, "공연 제목", "AAA1234-1238", "PERCENT", 20, expiredTime)
                        )
                );

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/business/coupons")
                        .content(objectMapper.writeValueAsString(createCouponRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("쿠폰 정보를 수정한다.")
    @Test
    void update() throws Exception {
        //given
        Long couponId = 1L;
        LocalDateTime expiredTime =
                LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        UpdateCouponRequest updateCouponRequest = new UpdateCouponRequest(
                1L,
                Coupon.Type.PERCENT,
                20,
                expiredTime,
                5
        );

        given(couponService.update(any(UpdateCouponValue.class)))
                .willReturn(
                        new UpdateCouponResult(
                                1L,
                                "공연 제목",
                                "AAA1234-1234",
                                "PERCENT",
                                20,
                                null,
                                expiredTime
                        )
                );

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/business/coupons/{id}", couponId)
                        .content(objectMapper.writeValueAsString(updateCouponRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("쿠폰을 삭제한다.")
    @Test
    void delete() throws Exception{
        //given
        Long couponId = 1L;

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/business/coupons/{id}", couponId))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
