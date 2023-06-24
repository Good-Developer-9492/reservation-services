package com.gd.reservationservices.domain.payment;

import com.gd.reservationservices.domain.BaseTimeEntity;
import com.gd.reservationservices.domain.performance.Performance;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
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


    public Coupon(Performance performance,
                  Type type,
                  Integer value,
                  LocalDateTime expiredAt) {
        this(null, performance, UUID.randomUUID().toString(), type, value, null, expiredAt);
    }

    public enum Type {
        PERCENT,
        WON
    }

    public Coupon delete() {
        this.expiredAt = LocalDateTime.now();
        return this;
    }

    public Coupon update(Performance performance,
                         Type type,
                         Integer value,
                         LocalDateTime expiredAt) {
        this.performance = performance;
        this.type = type;
        this.value = value;
        this.expiredAt = expiredAt;
        return this;
    }

    public Coupon use() {
        this.usedAt = LocalDateTime.now();
        return this;
    }

    public boolean isOverPrice(int value) {
        if (Coupon.Type.PERCENT.equals(this.type)) {
            return value > 100;
        }
        if (Coupon.Type.WON.equals(this.type)) {
            return value > this.performance.getPrice();
        }
        return false;
    }

    protected Coupon() {
    }

    public Coupon(Long id, Performance performance, String code, Type type, Integer value, LocalDateTime usedAt, LocalDateTime expiredAt) {
        this.id = id;
        this.performance = performance;
        this.code = code;
        this.type = type;
        this.value = value;
        this.usedAt = usedAt;
        this.expiredAt = expiredAt;
    }
}
