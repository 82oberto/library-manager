package com.roberto.library_manager.command;

import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteOneBookCommand extends GlobalCommand<Long, Void> {

    private final BookService bookService;

    @Override
    public Void doExecute(Long id) {
        bookService.deleteOne(id);
        return null;
    }

    @Override
    protected boolean canExecute(Long id) {
        return id != null && id > 0;
    }

}