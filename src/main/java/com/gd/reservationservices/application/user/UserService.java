package com.gd.reservationservices.application.user;

import com.gd.reservationservices.application.user.command.CreateUser;
import com.gd.reservationservices.infrastructure.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void joinBusinessUser(CreateUser createUser) {
        if (userRepository.exists(createUser.userId())) {
            throw new IllegalArgumentException("이미 등록된 아이디 입니다.");
        }

        userRepository.save(createUser.toEntity());
    }
}
