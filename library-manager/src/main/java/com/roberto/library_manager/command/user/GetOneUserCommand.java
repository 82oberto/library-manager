package com.roberto.library_manager.command.user;

import com.roberto.library_manager.command.GlobalCommand;
import com.roberto.library_manager.model.user.UserResponse;
import com.roberto.library_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetOneUserCommand extends GlobalCommand<String, UserResponse> {

    private final UserService userService;

    @Override
    protected UserResponse doExecute(String email) {
        return userService.getOne(email);
    }

    @Override
    protected boolean canExecute(String email) {
        return email != null && !email.isBlank();
    }
}
