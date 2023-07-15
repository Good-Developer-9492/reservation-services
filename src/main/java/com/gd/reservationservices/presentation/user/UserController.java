package com.gd.reservationservices.presentation.user;

import com.gd.reservationservices.application.user.UserService;
import com.gd.reservationservices.application.user.dto.SearchUserResult;
import com.gd.reservationservices.application.user.dto.UpdateUserResult;
import com.gd.reservationservices.common.response.EmptyResponse;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.user.reqeust.UpdateUserRequest;
import com.gd.reservationservices.presentation.user.request.CreateUserRequest;
import com.gd.reservationservices.presentation.user.response.SearchUserResponse;
import com.gd.reservationservices.presentation.user.response.UpdateUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public EmptyResponse join(@RequestBody CreateUserRequest createUserRequest) {
        userService.joinBusinessUser(createUserRequest.toValue());

        return new EmptyResponse.Ok<>();
    }

    @GetMapping("/{id}")
    public SingleResponse<SearchUserResponse> searchBy(@PathVariable Long id) {
        SearchUserResult searchUserResult = userService.searchBy(id);

        SearchUserResponse searchUserResponse = new SearchUserResponse(searchUserResult);

        return new SingleResponse.Ok<>(searchUserResponse);
    }

    @PutMapping("/{id}")
    public SingleResponse<UpdateUserResponse> update(@PathVariable Long id,
                                                     @RequestBody UpdateUserRequest updateUserRequest) {
        UpdateUserResult updateUserResult = userService.update(id, updateUserRequest.toValue());

        return new SingleResponse.Ok<>(
            new UpdateUserResponse(updateUserResult)
        );
    }
}
