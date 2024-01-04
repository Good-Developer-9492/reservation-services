package com.gd.reservationservices.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {
    @DisplayName("유저 정보를 수정한다.")
    @Test
    void updateInformation() {
        //given
        User user = new User(
                "user01",
                "user1234",
                "테스터",
                22,
                "user01@test.com",
                "01012345678",
                Role.CUSTOMER
        );
        //when
        user.updateInformation("user12345", "테스터수정", 20);

        //then
        assertThat(user.getUserPw()).isEqualTo("user12345");
        assertThat(user.getName()).isEqualTo("테스터수정");
        assertThat(user.getAge()).isEqualTo(20);
    }
}
