package com.gd.reservationservices.domain.user;

import com.gd.reservationservices.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int agw;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Role role;

    protected User() {
    }

    public User(String userId,
                String userPw,
                String name,
                int agw,
                String email,
                String phone,
                Role role) {
        this.userId = userId;
        this.userPw = userPw;
        this.name = name;
        this.agw = agw;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public void updateInformation(String userPw, String name, int age) {
        this.userPw = userPw;
        this.name = name;
        this.agw = age;
    }
}
