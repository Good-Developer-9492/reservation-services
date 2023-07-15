package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.ReservationService;
import com.gd.reservationservices.application.performance.dto.ReservationSearchListResult;
import com.gd.reservationservices.application.performance.dto.ReservationSearchResult;
import com.gd.reservationservices.common.request.PagingRequest;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.common.response.Paging;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.performance.response.ReservationResponse;
import lombok.RequiredArgsConstructor;
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
    public ListResponse<ReservationResponse> searchAllBy(@PathVariable Long performanceId,
                                                         PagingRequest pagingRequest) {
        ReservationSearchListResult reservation = reservationService.searchAllBy(performanceId, pagingRequest.toPageable());

        return new ListResponse.Ok<>(
            reservation.reservations().stream()
                .map(ReservationResponse::new)
                .collect(Collectors.toList()),
            new Paging(
                reservation.totalCount(),
                reservation.currentPage(),
                reservation.perPage(),
                reservation.totalPage()
            )
        );
    }

    @GetMapping("/{performanceId}/reservations/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<ReservationResponse> searchBy(@PathVariable Long performanceId,
                                                        @PathVariable Long reservationId) {
        ReservationSearchResult reservation = reservationService.searchBy(performanceId, reservationId);

        return new SingleResponse.Ok<>(
            new ReservationResponse(reservation)
        );
    }
}
