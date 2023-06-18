package com.gd.reservationservices.presentation.user;

import com.gd.reservationservices.application.user.UserService;
import com.gd.reservationservices.application.user.command.CreateUser;
import com.gd.reservationservices.common.response.EmptyResponse;
import com.gd.reservationservices.presentation.user.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public EmptyResponse join(@RequestBody CreateUserRequest createUserRequest) {
        userService.joinBusinessUser(
            new CreateUser(
                createUserRequest.userId(),
                createUserRequest.userPw(),
                createUserRequest.name(),
                createUserRequest.age(),
                createUserRequest.email(),
                createUserRequest.phone(),
                createUserRequest.role()
            ));

        return new EmptyResponse.Ok<>();
    }
}
