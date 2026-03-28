package com.roberto.library_manager.command;

import com.roberto.library_manager.exception.InputException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class GlobalCommand<I, O> {

    public O execute(I input) {
        if(!canExecute(input)){
            log.error("validation error");
            throw new InputException(input);
        }
        log.info("Executing command: {}", this.getClass().getSimpleName());
        O result = doExecute(input);
        log.info("Command completed: {}", this.getClass().getSimpleName());
        return result;
    }

    protected abstract O doExecute(I input);

    protected abstract boolean canExecute(I input);
}