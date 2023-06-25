package com.gd.reservationservices.domain.performance;

import java.util.ArrayList;
import java.util.List;

public class PerformanceSeatGroups {
    private final List<SeatInfo> performanceSeats;

    public boolean seatRegistrationAvailable(int maxSeats) {
        int sumSeats = performanceSeats.stream()
            .mapToInt(s -> s.seatCount)
            .sum();

        return maxSeats >= sumSeats;
    }

    public List<Seat> getSeats(Performance performance) {
        List<Seat> seats = new ArrayList<>();

        for (SeatInfo seatInfo : this.performanceSeats) {
            String location = seatInfo.location();
            int size = seatInfo.seatCount();

            for (int j = 1; j < size; j++) {
                seats.add(
                    new Seat(performance.getId(), location, j)
                );
            }
        }

        return seats;
    }

    public PerformanceSeatGroups(List<SeatInfo> performanceSeats) {
        this.performanceSeats = performanceSeats;
    }

    public record SeatInfo(String location, int seatCount) {
    }
}
