package com.roberto.library_manager.command.user;

import com.roberto.library_manager.command.GlobalCommand;
import com.roberto.library_manager.model.user.UserRequest;
import com.roberto.library_manager.model.user.UserResponse;
import com.roberto.library_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsertUserCommand extends GlobalCommand<UserRequest, UserResponse> {

    private final UserService userService;

    @Override
    protected UserResponse doExecute(UserRequest request) {
        return userService.save(request);
    }

    @Override
    protected boolean canExecute(UserRequest request) {
        return request != null;
    }
}
