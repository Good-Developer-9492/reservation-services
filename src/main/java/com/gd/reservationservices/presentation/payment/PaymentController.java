package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.PaymentService;
import com.gd.reservationservices.common.response.EmptyResponse;
import com.gd.reservationservices.presentation.payment.request.payment.CreatePaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public SingleResponse<SearchPaymentResponse> search(@PathVariable Long id) {
//        SearchPaymentResult result = paymentService(id);
//        return new SingleResponse.Ok<>(new SearchPaymentResponse(result));
//    }

//    @GetMapping("/{userId}")
//    @ResponseStatus(HttpStatus.OK)
//    public ListResponse<SearchPaymentResponse> searchAll(@PathVariable Long userId) {
//        List<SearchPaymentResult> result = paymentService.searchAll(userId);
//        return new ListResponse.Ok<>(
//                result.stream().map(SearchPaymentResponse::new).collect(Collectors.toList()),
//                null
//        );
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse pay(@RequestBody CreatePaymentRequest request) {
        paymentService.pay(request.toValue());
        return new EmptyResponse.Ok<>();
    }

//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public EmptyResponse refund(@PathVariable Long id,
//                                @RequestBody RefundPaymentRequest request) {
//        paymentService.refund(id, request.toValue());
//        return new EmptyResponse.Ok<>();
//    }
}
