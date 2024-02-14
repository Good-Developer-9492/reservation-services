package com.gd.reservationservices.domain.point;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class Point {
    private final Long id;
    private final Long userId;
    private final Type type;
    private final Long amount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Point(Long id, Long userId, Type type, Long amount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public enum Type {
        ADD,
        USED,
        CANCEL
    }
}
