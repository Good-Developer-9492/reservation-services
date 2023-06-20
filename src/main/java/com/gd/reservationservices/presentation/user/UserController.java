package com.gd.reservationservices.presentation.user;

import com.gd.reservationservices.application.user.UserService;
import com.gd.reservationservices.application.user.dto.SearchUser;
import com.gd.reservationservices.common.response.EmptyResponse;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.user.request.CreateUserRequest;
import com.gd.reservationservices.presentation.user.response.SearchUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public EmptyResponse join(@RequestBody CreateUserRequest createUserRequest) {
        userService.joinBusinessUser(createUserRequest.toValue());

        return new EmptyResponse.Ok<>();
    }

    @GetMapping("/users/{id}")
    public SingleResponse<SearchUserResponse> searchUser(@PathVariable Long id) {
        SearchUser searchUser = userService.searchUser(id);

        SearchUserResponse searchUserResponse = new SearchUserResponse(searchUser);

        return new SingleResponse.Ok<>(searchUserResponse);
    }
}
