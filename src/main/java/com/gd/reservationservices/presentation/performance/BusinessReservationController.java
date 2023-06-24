package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.ReservationService;
import com.gd.reservationservices.common.request.PagingRequest;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.common.response.Paging;
import com.gd.reservationservices.domain.performance.Reservation;
import com.gd.reservationservices.presentation.performance.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/business/reservations")
@RequiredArgsConstructor
public class BusinessReservationController {
    private final ReservationService reservationService;

    @GetMapping("/performance/{performanceId}")
    public ResponseEntity<ListResponse<ReservationResponse>> getAllReservations(@PathVariable Long performanceId,
                                                                                PagingRequest pagingRequest) {
        Page<Reservation> reservation = reservationService.getAllReservations(performanceId, pagingRequest.toPageable());

        return ResponseEntity.ok(
            new ListResponse.Ok<>(
                reservation.get().map(ReservationResponse::new).collect(Collectors.toList()),
                new Paging(
                    reservation.getTotalElements(),
                    reservation.getPageable().getPageNumber(),
                    reservation.getPageable().getPageSize(),
                    reservation.getTotalPages()
                )
            )
        );
    }
}
