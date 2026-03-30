package com.roberto.library_manager.command.user;

import com.roberto.library_manager.command.GlobalCommand;
import com.roberto.library_manager.model.user.User;
import com.roberto.library_manager.model.user.UserResponse;
import com.roberto.library_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InsertUserCommand extends GlobalCommand<User, UserResponse> {

    private final UserService userService;

    @Override
    protected UserResponse doExecute(User user) {
        return userService.save(user);
    }

    @Override
    protected boolean canExecute(User user) {
        return user != null;
    }
}
