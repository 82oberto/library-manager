package com.roberto.library_manager.command.user;

import com.roberto.library_manager.command.GlobalCommand;
import com.roberto.library_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAllUsersCommand extends GlobalCommand<Void, Void> {

    private final UserService userService;

    @Override
    protected Void doExecute(Void input) {
        userService.deleteAll();
        return null;
    }

    @Override
    protected boolean canExecute(Void input) {
        return true;
    }
}
