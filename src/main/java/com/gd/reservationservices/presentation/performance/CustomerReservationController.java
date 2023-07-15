package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.ReservationService;
import com.gd.reservationservices.application.performance.dto.CreateReservationResult;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.performance.request.ReservationCreateRequest;
import com.gd.reservationservices.presentation.performance.response.CreateReservationResponse;
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
    public SingleResponse<CreateReservationResponse> create(@PathVariable Long performanceId,
                                                            @RequestBody ReservationCreateRequest request) {
        CreateReservationResult result = reservationService.create(performanceId, request.toValue());

        return new SingleResponse.Ok<>(
            new CreateReservationResponse(result)
        );
    }
}
