package com.gd.reservationservices.application.performance;

import com.gd.reservationservices.domain.performance.Performance;

import java.time.LocalDateTime;

public class PerformanceFixture {
    public static Performance create() {
        return new Performance(
                PlaceFixture.create(),
                Performance.Category.SPORT,
                LocalDateTime.now().plusDays(7),
                LocalDateTime.now().plusDays(7).plusHours(3),
                LocalDateTime.now().plusDays(3),
                LocalDateTime.now().plusDays(6),
                "공연 이름",
                "공연 내용",
                "출연진",
                Performance.FilmRating.TWELVE,
                50000,
                null,
                null
        );
    }

}
