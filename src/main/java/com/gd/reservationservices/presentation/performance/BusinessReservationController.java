package com.gd.reservationservices.presentation.performance;

import com.gd.reservationservices.application.performance.ReservationService;
import com.gd.reservationservices.application.performance.dto.SearchReservationListResult;
import com.gd.reservationservices.application.performance.dto.SearchReservationResult;
import com.gd.reservationservices.common.request.PagingRequest;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.common.response.Paging;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.domain.performance.Seat;
import com.gd.reservationservices.presentation.performance.response.SearchReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/business/performances")
@RequiredArgsConstructor
public class BusinessReservationController {
    private final ReservationService reservationService;

    @GetMapping("/{performanceId}/reservations")
    @ResponseStatus(HttpStatus.OK)
    public ListResponse<SearchReservationResponse> searchAllBy(@PathVariable Long performanceId,
                                                               PagingRequest pagingRequest) {
        SearchReservationListResult reservation = reservationService.searchAllBy(performanceId, pagingRequest.toPageable());

        return new ListResponse.Ok<>(
            reservation.reservations().stream()
                .map(SearchReservationResponse::new)
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
    public SingleResponse<SearchReservationResponse> searchBy(@PathVariable Long performanceId,
                                                              @PathVariable Long reservationId) {
        SearchReservationResult reservation = reservationService.searchBy(performanceId, reservationId);

        return new SingleResponse.Ok<>(
            new SearchReservationResponse(reservation)
        );
    }

    @GetMapping("/{performanceId}/seats")
    @ResponseStatus(HttpStatus.OK)
    public List<Seat> searchAllSeatBy(@PathVariable Long performanceId, PagingRequest pagingRequest) {
        List<Seat> seats = reservationService.searchAllSeatBy(performanceId, pagingRequest.toPageable());
        return seats;
    }
}
