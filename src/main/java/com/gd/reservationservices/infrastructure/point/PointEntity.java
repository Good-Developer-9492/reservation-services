package com.gd.reservationservices.infrastructure.point;

import com.gd.reservationservices.domain.BaseTimeEntity;
import com.gd.reservationservices.domain.point.Point;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "point")
public class PointEntity extends BaseTimeEntity {
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

    protected PointEntity() {
    }

    public PointEntity(Long userId, Type type, Long amount) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
    }

    public Point toModel() {
        return new Point(
            id,
            userId,
            Point.Type.valueOf(type.toString()),
            amount,
            getCreatedAt(),
            getUpdatedAt()
        );
    }

    public enum Type {
        ADD,
        USED,
        CANCEL
    }

}
