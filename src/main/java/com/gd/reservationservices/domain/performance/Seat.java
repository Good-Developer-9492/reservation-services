package com.gd.reservationservices.domain.performance;

import com.gd.reservationservices.domain.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
public class Seat extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long performanceId;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Boolean isReserved;

    protected Seat() {
    }

    public Seat(Long performanceId, String location, Integer number, Boolean isReserved) {
        this.performanceId = performanceId;
        this.location = location;
        this.number = number;
        this.isReserved = isReserved;
    }

    public Seat(Long performanceId, String Location, Integer number) {
        this(performanceId, Location, number, false);
    }

    public Boolean isReserved() {
        return Boolean.TRUE.equals(this.isReserved);
    }

    public void reserve() {
        this.isReserved = true;
    }
}
