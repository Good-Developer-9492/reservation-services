package com.gd.reservationservices.domain.performance;

import com.gd.reservationservices.domain.BaseTimeEntity;
import com.gd.reservationservices.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "performance_id", nullable = false)
    private Performance performance;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Column(nullable = false)
    private LocalDateTime reservedAt;

    protected Reservation() {
    }

    public Reservation(User user, Performance performance, Seat seat, LocalDateTime reservedAt) {
        this.user = user;
        this.performance = performance;
        this.seat = seat;
        this.reservedAt = reservedAt;
    }
}
