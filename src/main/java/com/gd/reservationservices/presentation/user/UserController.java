package com.gd.reservationservices.presentation.user;

import com.gd.reservationservices.application.user.UserService;
import com.gd.reservationservices.application.user.dto.SavedUserValue;
import com.gd.reservationservices.application.user.dto.SearchUserResult;
import com.gd.reservationservices.application.user.dto.UpdateUserResult;
import com.gd.reservationservices.common.response.SingleResponse;
import com.gd.reservationservices.presentation.user.reqeust.UpdateUserRequest;
import com.gd.reservationservices.presentation.user.request.CreateUserRequest;
import com.gd.reservationservices.presentation.user.response.SavedUserResponse;
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
    public SingleResponse<SavedUserResponse> join(@RequestBody CreateUserRequest createUserRequest) {
        SavedUserValue savedUser = userService.join(createUserRequest.toValue());
        SavedUserResponse savedUserResponse = new SavedUserResponse(savedUser);

        return new SingleResponse.Ok<>(savedUserResponse);
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
