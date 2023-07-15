package com.gd.reservationservices.application.user;

import com.gd.reservationservices.application.user.command.CreateUser;
import com.gd.reservationservices.application.user.dto.SearchUserResult;
import com.gd.reservationservices.application.user.dto.UpdateUserResult;
import com.gd.reservationservices.application.user.dto.UpdateUserCommend;
import com.gd.reservationservices.application.user.exception.UserNotFoundException;
import com.gd.reservationservices.domain.user.User;
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

    public SearchUserResult searchBy(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        return new SearchUserResult(user);
    }

    @Transactional
    public UpdateUserResult update(Long userId, UpdateUserCommend updateUserCommend) {
        User user = userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

        user.updateInformation(
            updateUserCommend.userPw(),
            updateUserCommend.name(),
            updateUserCommend.age()
        );

        return new UpdateUserResult(user);
    }
}
