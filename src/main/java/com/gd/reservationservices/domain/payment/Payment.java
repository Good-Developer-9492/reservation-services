package com.gd.reservationservices.domain.payment;

import com.gd.reservationservices.domain.BaseTimeEntity;
import com.gd.reservationservices.domain.performance.Performance;
import com.gd.reservationservices.domain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    @OneToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer discountPrice;

    @Column
    private LocalDateTime canceledAt;

    protected Payment() {
    }

    public Payment(User user,
                   Performance performance,
                   Coupon coupon,
                   Integer price,
                   Integer discountPrice,
                   LocalDateTime canceledAt) {
        this.user = user;
        this.performance = performance;
        this.coupon = coupon;
        this.price = price;
        this.discountPrice = discountPrice;
        this.canceledAt = canceledAt;
    }
}
