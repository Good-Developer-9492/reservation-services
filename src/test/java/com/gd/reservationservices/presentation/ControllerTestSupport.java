package com.gd.reservationservices.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.reservationservices.application.payment.CouponService;
import com.gd.reservationservices.application.performance.PerformanceService;
import com.gd.reservationservices.application.performance.ReservationService;
import com.gd.reservationservices.application.user.UserService;
import com.gd.reservationservices.presentation.payment.BusinessCouponController;
import com.gd.reservationservices.presentation.payment.CustomerCouponController;
import com.gd.reservationservices.presentation.performance.BusinessReservationController;
import com.gd.reservationservices.presentation.performance.CustomerReservationController;
import com.gd.reservationservices.presentation.performance.PerformanceController;
import com.gd.reservationservices.presentation.user.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = {
        UserController.class,
        PerformanceController.class,
        CustomerReservationController.class,
        BusinessReservationController.class,
        CustomerCouponController.class,
        BusinessCouponController.class
})
public abstract class ControllerTestSupport {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected UserService userService;

    @MockBean
    protected PerformanceService performanceService;

    @MockBean
    protected ReservationService reservationService;

    @MockBean
    protected CouponService couponService;
}
