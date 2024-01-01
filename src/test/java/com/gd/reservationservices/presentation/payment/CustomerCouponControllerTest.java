package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.dto.UseCouponResult;
import com.gd.reservationservices.presentation.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class CustomerCouponControllerTest extends ControllerTestSupport {
    @DisplayName("발급받은 쿠폰을 사용한다.")
    @Test
    void use() throws Exception{
        //given
        Long couponId = 1L;
        LocalDateTime useTIme =
                LocalDateTime.of(2023, 8, 12, 21, 20, 0);
        LocalDateTime expiredTime =
                LocalDateTime.of(2023, 8, 19, 23, 59, 59);

        UseCouponResult useCouponResult =
                new UseCouponResult(1L, "AAA-1234-1234", "PERCENT", 15, useTIme, expiredTime);

        given(couponService.use(any(Long.class), any(LocalDateTime.class)))
                        .willReturn(useCouponResult);

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/customer/coupons/{id}", couponId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
