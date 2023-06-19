package com.gd.reservationservices.presentation.user;

import com.gd.reservationservices.application.user.UserService;
import com.gd.reservationservices.application.user.dto.UpdateUser;
import com.gd.reservationservices.application.user.dto.UpdateUserCommend;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.user.reqeust.UpdateUserRequest;
import com.gd.reservationservices.presentation.user.response.UpdateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/users")
    public SingleResponse<UpdateUserResponse> update(@RequestBody UpdateUserRequest updateUserRequest) {

        UpdateUser updateUser =
                userService.update(new UpdateUserCommend(
                        updateUserRequest.id(),
                        updateUserRequest.userPw(),
                        updateUserRequest.name(),
                        updateUserRequest.age()
                ));

        UpdateUserResponse updateUserResponse = new UpdateUserResponse(
                updateUser.userId(),
                updateUser.name(),
                updateUser.age(),
                updateUser.email(),
                updateUser.phone(),
                updateUser.role());

        return new SingleResponse.Ok<>(updateUserResponse);
    }

}
