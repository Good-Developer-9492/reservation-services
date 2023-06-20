package com.gd.reservationservices.application.user;

import com.gd.reservationservices.application.user.dto.SearchUser;
import com.gd.reservationservices.application.user.exception.UserNotFoundException;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.infrastructure.user.UserRepository;
import com.gd.reservationservices.infrastructure.user.value.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public SearchUser searchUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);

        return new SearchUser(user);
    }
}
