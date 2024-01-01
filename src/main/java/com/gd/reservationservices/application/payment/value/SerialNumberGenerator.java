package com.gd.reservationservices.application.payment.value;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class SerialNumberGenerator {
    private final List<String> list;

    public SerialNumberGenerator(int amount) {
        this(generate(amount));
    }

    public SerialNumberGenerator(List<String> list) {
        this.list = list;
    }

    private static List<String> generate(int amount) {
        List<String> serialNumbers = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            serialNumbers.add(UUID.randomUUID().toString());
        }

        return serialNumbers;
    }
}
