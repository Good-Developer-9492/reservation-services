package com.gd.reservationservices.application.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutPaymentService {
    public void call() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
    }

}
