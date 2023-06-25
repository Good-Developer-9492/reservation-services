package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.ReservationService;
import com.gd.reservationservices.common.request.PagingRequest;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.common.response.Paging;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.presentation.performance.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/business/performances")
@RequiredArgsConstructor
public class BusinessReservationController {
    private final ReservationService reservationService;

    @GetMapping("/{performanceId}/reservations")
    @ResponseStatus(HttpStatus.OK)
    public ListResponse<ReservationResponse> getAllReservations(@PathVariable Long performanceId,
                                                                PagingRequest pagingRequest) {
        Page<Reservation> reservation = reservationService.getAllReservations(performanceId, pagingRequest.toPageable());

        return new ListResponse.Ok<>(
            reservation.get().map(ReservationResponse::new).collect(Collectors.toList()),
            new Paging(
                reservation.getTotalElements(),
                reservation.getPageable().getPageNumber(),
                reservation.getPageable().getPageSize(),
                reservation.getTotalPages()
            )
        );
    }

    @GetMapping("/{performanceId}/reservations/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<ReservationResponse> getReservation(@PathVariable Long performanceId,
                                                              @PathVariable Long reservationId) {
        Reservation reservation = reservationService.getReservation(performanceId, reservationId);

        return new SingleResponse.Ok<>(
            new ReservationResponse(reservation)
        );
    }
}
