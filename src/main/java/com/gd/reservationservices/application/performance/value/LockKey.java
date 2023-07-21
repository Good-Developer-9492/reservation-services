package com.gd.reservationservices.application.performance.value;

public class LockKey {
    private final Long performanceId;
    private final String seatLocation;
    private final Integer number;

    public LockKey(Long performanceId, String seatLocation, Integer number) {
        this.performanceId = performanceId;
        this.seatLocation = seatLocation;
        this.number = number;
    }

    public String combination() {
        return performanceId + seatLocation + number;
    }
}
