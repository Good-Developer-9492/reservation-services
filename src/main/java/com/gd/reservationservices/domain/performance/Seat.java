package com.gd.reservationservices.domain.performance;

import com.gd.reservationservices.domain.BaseTimeEntity;
import jakarta.persistence.*;

@Entity
public class Seat extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private Boolean isReserved;

    protected Seat() {
    }

    public Seat(Performance performance, String Location, Integer number) {
        this(performance, Location, number, false);
    }

    public Seat(Performance performance, String location, Integer number, Boolean isReserved) {
        this.performance = performance;
        this.location = location;
        this.number = number;
        this.isReserved = isReserved;
    }
}
