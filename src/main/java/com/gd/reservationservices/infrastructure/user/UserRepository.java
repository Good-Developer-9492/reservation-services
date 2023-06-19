package com.gd.reservationservices.infrastructure.user;

import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.infrastructure.user.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findById(Long id);
}
