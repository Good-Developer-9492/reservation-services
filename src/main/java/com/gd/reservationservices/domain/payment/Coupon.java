package com.gd.reservationservices.domain.payment;

import com.gd.reservationservices.domain.BaseTimeEntity;
import com.gd.reservationservices.domain.performance.Performance;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
public class Coupon extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    private Performance performance;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private Integer discountValue;

    @Column
    private LocalDateTime usedAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;


    public Coupon(Performance performance,
                  String serialNumber,
                  Type type,
                  Integer discountValue,
                  LocalDateTime expiredAt) {
        this(null,
                performance,
                serialNumber,
                type,
                discountValue,
                null,
                expiredAt);
    }

    public enum Type {
        PERCENT,
        WON
    }

    public boolean isOverDiscountValue(int value) {
        if (Type.PERCENT.equals(this.type)) {
            return value > 100;
        }
        if (Type.WON.equals(this.type)) {
            return value > this.performance.getPrice();
        }
        return false;
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
        this.discountValue = value;
        this.expiredAt = expiredAt;
        return this;
    }

    public Coupon use(LocalDateTime useTime) {
        this.usedAt = useTime;
        return this;
    }

    protected Coupon() {
    }

    public Coupon(Long id, Performance performance, String serialNumber, Type type, Integer discountValue, LocalDateTime usedAt, LocalDateTime expiredAt) {
        this.id = id;
        this.performance = performance;
        this.serialNumber = serialNumber;
        this.type = type;
        this.discountValue = discountValue;
        this.usedAt = usedAt;
        this.expiredAt = expiredAt;
    }
}