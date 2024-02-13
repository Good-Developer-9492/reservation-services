package com.gd.reservationservices.domain.point;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private Long amount;

    protected Point() {
    }

    public Point(Long userId, Type type, Long amount) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
    }

    public enum Type {
        ADD,
        USED,
        CANCEL
    }
}
