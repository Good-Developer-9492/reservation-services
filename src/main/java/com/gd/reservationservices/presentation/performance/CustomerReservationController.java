package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.ReservationService;
import com.gd.reservationservices.common.response.EmptyResponse;
import com.gd.reservationservices.presentation.performance.request.ReservationCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/performances")
@RequiredArgsConstructor
public class CustomerReservationController {
    private final ReservationService reservationService;

    @PostMapping("/{performanceId}/reservations")
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse createReservation(@PathVariable Long performanceId,
                                           @RequestBody ReservationCreateRequest request) {
        reservationService.createReservation(performanceId, request.toValue());

        return new EmptyResponse.Ok<>();
    }
}
