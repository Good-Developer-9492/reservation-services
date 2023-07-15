package com.gd.reservationservices.presentation.payment;

import com.gd.reservationservices.application.payment.CouponService;
import com.gd.reservationservices.common.response.EmptyResponse;
import com.gd.reservationservices.common.response.ListResponse;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.payment.request.CreateCouponRequest;
import com.gd.reservationservices.presentation.payment.request.UpdateCouponRequest;
import com.gd.reservationservices.presentation.payment.response.CreateCouponResponse;
import com.gd.reservationservices.presentation.payment.response.SearchCouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/business/coupons")
@RequiredArgsConstructor
public class BusinessCouponController {
    private final CouponService couponService;

    //todo 따닥 이슈 고민해보기
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ListResponse<CreateCouponResponse> create(@RequestBody CreateCouponRequest request) {
        List<CreateCouponResponse> result = couponService.create(request.toValue())
                .stream()
                .map(CreateCouponResponse::new)
                .collect(Collectors.toList());

        return new ListResponse.Ok<>(result, null);
    }

    //todo 추후에 id가 아닌 쿠폰번호로 조회하기
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<SearchCouponResponse> search(@PathVariable Long id) {
        SearchCouponResponse result = new SearchCouponResponse(couponService.search(id));
        return new SingleResponse.Ok<>(result);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse update(@PathVariable Long id,
                                @RequestBody UpdateCouponRequest request) {
        couponService.update(request.toValue(id));
        return new EmptyResponse.Ok<>();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmptyResponse delete(@PathVariable Long id) {
        couponService.delete(id);
        return new EmptyResponse.Ok<>();
    }

}