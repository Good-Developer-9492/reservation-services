package com.gd.reservationservices.domain.performance;

import com.gd.reservationservices.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Place extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Integer maxSeat;

    protected Place() {
    }

    public Place(String name, String location, Integer maxSeat) {
        this.name = name;
        this.location = location;
        this.maxSeat = maxSeat;
    }
}
