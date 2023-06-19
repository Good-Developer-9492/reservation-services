package com.gd.reservationservices.application.user;

import com.gd.reservationservices.application.user.dto.UpdateUser;
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
    public UpdateUser update(UpdateUserCommend updateUserCommend) {
        User user = userRepository.findById(updateUserCommend.id())
                .orElseThrow(UserNotFoundException::new);

        user.correctionOfInformation(
                updateUserCommend.userPw(),
                updateUserCommend.name(),
                updateUserCommend.age()
        );

        return new UpdateUser(
                user.getUserId(),
                user.getName(),
                user.getAgw(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );
    }
}
