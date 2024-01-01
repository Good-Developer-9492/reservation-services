package com.gd.reservationservices.application.user;

import com.gd.reservationservices.IntegrationTestSupport;
import com.gd.reservationservices.application.user.command.CreateUser;
import com.gd.reservationservices.application.user.dto.SavedUserValue;
import com.gd.reservationservices.application.user.dto.SearchUserResult;
import com.gd.reservationservices.application.user.dto.UpdateUserResult;
import com.gd.reservationservices.application.user.dto.UpdateUserValue;
import com.gd.reservationservices.common.exception.ErrorCode;
import com.gd.reservationservices.domain.user.Role;
import com.gd.reservationservices.domain.user.User;
import com.gd.reservationservices.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest extends IntegrationTestSupport {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @DisplayName("사용자가 신규 등록 한다.")
    @Test
    void join() {
        //given
        CreateUser createUserRequest = new CreateUser(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345679",
                Role.CUSTOMER.toString()
        );

        //when
        SavedUserValue savedUser = userService.join(createUserRequest);

        //then
        assertThat(savedUser)
                .extracting("userId", "name", "age", "email", "phone")
                .contains("user01", "user01", 22, "user01@test.com", "01012345679");
    }

    @DisplayName("이미 등록된 아이디가 있으면 예외 발생한다.")
    @Test
    void joinWithDuplicate() {
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

        CreateUser createUserRequest = new CreateUser(
                "user01",
                "user12345",
                "user02",
                25,
                "user02@test.com",
                "01012345678",
                Role.CUSTOMER.toString()
        );

        //when
        //then
        assertThatThrownBy(() -> userService.join(createUserRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.valueOf(ErrorCode.ALREADY_REGISTERED_USER));
    }

    @DisplayName("사용자를 조회한다.")
    @Test
    void searchBy() {
        //given
        User user = new User(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345677",
                Role.CUSTOMER
        );
        User savedUser = userRepository.save(user);
        Long userId = savedUser.getId();

        //when
        SearchUserResult retrievedUser = userService.searchBy(userId);

        //then
        assertThat(retrievedUser)
                .extracting("userId", "name", "age", "email", "phone")
                .contains("user01", "user01", 22, "user01@test.com", "01012345677");
    }

    @DisplayName("조회한 사용자 데이터가 없을 경우 예외를 발생한다.")
    @Test
    void searchByWithoutUser() {
        //given
        Long userId = 1L;

        //when
        //then
        assertThatThrownBy(() -> userService.searchBy(userId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.valueOf(ErrorCode.USER_NOT_FOUND));
    }

    @DisplayName("사용자 정보를 수정한다.")
    @Test
    void update() {
        //given
        User user = new User(
                "user01",
                "user1234",
                "user01",
                22,
                "user01@test.com",
                "01012345677",
                Role.CUSTOMER
        );
        User savedUser = userRepository.save(user);
        Long savedUserId = savedUser.getId();

        UpdateUserValue updateUserValue =
                new UpdateUserValue("test12345", "테스터수정", 21);

        //when
        UpdateUserResult updatedUser = userService.update(savedUserId, updateUserValue);

        //then
        assertThat(updatedUser)
                .extracting("userId", "name", "age", "email", "phone")
                .contains("user01", "테스터수정", 21, "user01@test.com", "01012345677");
    }

    @DisplayName("수정하려는 사용자 정보가 없으면 예외 발생한다.")
    @Test
    void updateWithoutUser() {
        //given
        Long savedUserId = 1L;

        UpdateUserValue updateUserValue =
                new UpdateUserValue("test12345", "테스터수정", 21);

        //when
        //then
        assertThatThrownBy(() -> userService.update(savedUserId, updateUserValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.valueOf(ErrorCode.USER_NOT_FOUND));
    }
}
