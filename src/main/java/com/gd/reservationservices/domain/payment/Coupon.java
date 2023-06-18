package com.gd.reservationservices.domain.payment;

import com.gd.reservationservices.domain.BaseTimeEntity;
import com.gd.reservationservices.domain.performance.Performance;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Coupon extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private Integer value;

    @Column
    private LocalDateTime usedAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    protected Coupon() {
    }

    public Coupon(Performance performance,
                  String code,
                  Type type,
                  Integer value,
                  LocalDateTime usedAt,
                  LocalDateTime expiredAt) {
        this.performance = performance;
        this.code = code;
        this.type = type;
        this.value = value;
        this.usedAt = usedAt;
        this.expiredAt = expiredAt;
    }

    public enum Type {
        PERCENT,
        WON
    }
}
