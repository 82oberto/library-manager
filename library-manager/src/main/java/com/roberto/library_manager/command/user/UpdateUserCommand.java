package com.roberto.library_manager.command.user;

import com.roberto.library_manager.command.GlobalCommand;
import com.roberto.library_manager.model.user.UserRequest;
import com.roberto.library_manager.model.user.UserResponse;
import com.roberto.library_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class UpdateUserCommand extends GlobalCommand<Map.Entry<String, UserRequest>, UserResponse> {

    private final UserService userService;

    @Override
    protected UserResponse doExecute(Map.Entry<String, UserRequest> input) {
        return userService.update(input.getKey(), input.getValue());
    }

    @Override
    protected boolean canExecute(Map.Entry<String, UserRequest> input) {
        return input != null && input.getKey() != null && !input.getKey().isBlank() && input.getValue() != null;
    }
}
