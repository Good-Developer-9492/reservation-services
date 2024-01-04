package com.gd.reservationservices.infrastructure.user.custom;

import com.gd.reservationservices.IntegrationTestSupport;
import com.gd.reservationservices.domain.user.Role;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryCustomTest extends IntegrationTestSupport {
    @Autowired
    private UserRepository userRepository;

    @DisplayName("이미 등록된 아이디가 있는지 확인한다.")
    @Test
    void existsUserId() {
        //given
        User user = new User(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345678",
                Role.CUSTOMER
        );
        userRepository.save(user);
        String newUserId = "user02";

        //when
        Boolean exists = userRepository.exists(newUserId);

        //then
        assertThat(exists).isFalse();
    }

    @DisplayName("이미 등록된 아이디가 있으면 TRUE를 반호나한다.")
    @Test
    void existsUserIdWithDuplicate() {
        //given
        User user = new User(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345678",
                Role.CUSTOMER
        );
        userRepository.save(user);
        String duplicateUserId = "user01";

        //when
        Boolean exists = userRepository.exists(duplicateUserId);

        //then
        assertThat(exists).isTrue();
    }
}
