package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.PaymentService;
import com.gd.reservationservices.application.payment.dto.SearchPaymentResult;
import com.gd.reservationservices.common.response.EmptyResponse;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.payment.request.payment.CreatePaymentRequest;
import com.gd.reservationservices.presentation.payment.request.payment.RefundPaymentRequest;
import com.gd.reservationservices.presentation.payment.response.SearchPaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<SearchPaymentResponse> search(@PathVariable Long id) {
        SearchPaymentResult result = paymentService.search(id);
        return new SingleResponse.Ok<>(new SearchPaymentResponse(result));
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ListResponse<SearchPaymentResponse> searchAll(@PathVariable Long userId) {
        List<SearchPaymentResult> result = paymentService.searchAll(userId);
        return new ListResponse.Ok<>(
                result.stream().map(SearchPaymentResponse::new).collect(Collectors.toList()),
                null
        );
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse pay(@RequestBody CreatePaymentRequest request) {
        paymentService.pay(request.toValue());
        return new EmptyResponse.Ok<>();
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse refund(@PathVariable Long id,
                                @RequestBody RefundPaymentRequest request) {
        paymentService.refund(id, request.toValue());
        return new EmptyResponse.Ok<>();
    }
}
