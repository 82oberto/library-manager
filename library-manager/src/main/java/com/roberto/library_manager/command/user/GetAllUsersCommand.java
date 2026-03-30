package com.roberto.library_manager.command.user;

import com.roberto.library_manager.command.GlobalCommand;
import com.roberto.library_manager.model.user.UserResponse;
import com.roberto.library_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllUsersCommand extends GlobalCommand<Void, List<UserResponse>> {

    private final UserService userService;

    @Override
    protected List<UserResponse> doExecute(Void input) {
        return userService.getAll();
    }

    @Override
    protected boolean canExecute(Void input) {
        return true;
    }
}
