package com.gd.reservationservices.domain.performance;

import com.gd.reservationservices.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Performance extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "place_id", nullable = false)
    private Place place;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    @Column(nullable = false)
    private LocalDateTime startReservationAt;

    @Column(nullable = false)
    private LocalDateTime endReservationAt;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String acting;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FilmRating filmRating;

    @Column(nullable = false)
    private int price;

    @Column
    private LocalDateTime canceledAt;

    @Column
    private String cancelReason;

    public Performance(Place place,
                       Category category,
                       LocalDateTime startAt,
                       LocalDateTime endAt,
                       LocalDateTime startReservationAt,
                       LocalDateTime endReservationAt,
                       String title,
                       String content,
                       String acting,
                       FilmRating filmRating,
                       int price) {
        this(
                place,
                category,
                startAt,
                endAt,
                startReservationAt,
                endReservationAt,
                title,
                content,
                acting,
                filmRating,
                price,
                null,
                null
        );
    }

    protected Performance() {
    }

    public Performance(Place place,
                       Category category,
                       LocalDateTime startAt,
                       LocalDateTime endAt,
                       LocalDateTime startReservationAt,
                       LocalDateTime endReservationAt,
                       String title,
                       String content,
                       String acting,
                       FilmRating filmRating,
                       int price,
                       LocalDateTime canceledAt,
                       String cancelReason) {
        this.place = place;
        this.category = category;
        this.startAt = startAt;
        this.endAt = endAt;
        this.startReservationAt = startReservationAt;
        this.endReservationAt = endReservationAt;
        this.title = title;
        this.content = content;
        this.acting = acting;
        this.filmRating = filmRating;
        this.price = price;
        this.canceledAt = canceledAt;
        this.cancelReason = cancelReason;
    }

    public enum Category {
        MUSICAL,
        CONCERT,
        PLAY,
        CLASSIC,
        SPORT
    }

    public enum FilmRating {
        ALL,
        SEVEN,
        TWELVE,
        FIFTEEN,
        NINETEEN
    }
}
