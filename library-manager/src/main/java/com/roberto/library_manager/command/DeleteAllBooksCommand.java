package com.roberto.library_manager.command;

import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Command handler for deleting the entire list of books.
 * Validates input and delegates to {@link BookService}.
 */
@Component
@RequiredArgsConstructor
public class DeleteAllBooksCommand extends GlobalCommand<Void, Void> {

    private final BookService bookService;

    public Void doExecute(Void input) {
        bookService.deleteAll();
        return null;
    }

    @Override
    protected boolean canExecute(Void input) {
        return true;
    }
}