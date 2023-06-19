package com.gd.reservationservices.presentation.user;

import com.gd.reservationservices.application.user.UserService;
import com.gd.reservationservices.application.user.dto.SearchUser;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.user.response.SearchUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users/{id}")
    public SingleResponse<SearchUserResponse> join(@PathVariable Long id) {
        SearchUser searchUser = userService.searchUser(id);

        SearchUserResponse searchUserResponse =
                new SearchUserResponse(
                        searchUser.userId(),
                        searchUser.name(),
                        searchUser.age(),
                        searchUser.email(),
                        searchUser.phone(),
                        searchUser.role());

        return new SingleResponse.Ok<>(searchUserResponse);
    }
}
