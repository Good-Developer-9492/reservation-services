package com.gd.reservationservices.application.user;

import com.gd.reservationservices.application.user.command.CreateUser;
import com.gd.reservationservices.application.user.dto.SearchUserResult;
import com.gd.reservationservices.application.user.dto.UpdateUserCommend;
import com.gd.reservationservices.application.user.dto.UpdateUserResult;
import com.gd.reservationservices.common.exception.ErrorCode;
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
            throw new IllegalArgumentException(ErrorCode.ALREADY_REGISTERED_USER.getMessage());
        }

        userRepository.save(createUser.toEntity());
    }

    public SearchUserResult searchBy(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUND.getMessage()));

        return new SearchUserResult(user);
    }

    @Transactional
    public UpdateUserResult update(Long userId, UpdateUserCommend updateUserCommend) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException(ErrorCode.USER_NOT_FOUND.getMessage()));

        user.updateInformation(
            updateUserCommend.userPw(),
            updateUserCommend.name(),
            updateUserCommend.age()
        );

        return new UpdateUserResult(user);
    }
}
