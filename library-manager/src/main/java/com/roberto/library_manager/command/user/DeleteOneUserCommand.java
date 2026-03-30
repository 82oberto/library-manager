package com.roberto.library_manager.command.user;

import com.roberto.library_manager.command.GlobalCommand;
import com.roberto.library_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteOneUserCommand extends GlobalCommand<String, Void> {

    private final UserService userService;

    @Override
    protected Void doExecute(String email) {
        userService.delete(email);
        return null;
    }

    @Override
    protected boolean canExecute(String email) {
        return email != null && !email.isBlank();
    }
}
