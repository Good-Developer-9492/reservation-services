package com.gd.reservationservices.domain.payment;

import com.gd.reservationservices.domain.BaseTimeEntity;
import com.gd.reservationservices.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Long performanceId;

    private Long couponId;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer discountPrice;

    @Column
    private LocalDateTime canceledAt;

    protected Payment() {
    }

    public Payment(User user, Long performanceId, Long couponId, Integer price, Integer discountPrice) {
        this(null, user, performanceId, couponId, price, discountPrice, null);
    }

    private Payment(Long id,
                    User user,
                    Long performanceId,
                    Long couponId,
                    Integer price,
                    Integer discountPrice,
                    LocalDateTime canceledAt) {
        this.id = id;
        this.user = user;
        this.performanceId = performanceId;
        this.couponId = couponId;
        this.price = price;
        this.discountPrice = discountPrice;
        this.canceledAt = canceledAt;
    }

    public void validPrice(Integer performancePrice) {
        if(this.price < performancePrice - discountPrice) throw new RuntimeException();
    }

}
