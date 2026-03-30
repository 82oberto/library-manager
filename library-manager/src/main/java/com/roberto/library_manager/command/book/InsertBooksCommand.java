package com.roberto.library_manager.command.book;

import com.roberto.library_manager.command.GlobalCommand;
import com.roberto.library_manager.model.book.BookRequest;
import com.roberto.library_manager.model.book.InsertBooksResult;
import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Command handler for inserting a list of books.
 * Validates input and delegates to {@link BookService}.
 */
@Component
@RequiredArgsConstructor
public class InsertBooksCommand extends GlobalCommand<List<BookRequest>, InsertBooksResult> {

    private final BookService bookService;

    @Override
    public InsertBooksResult doExecute(List<BookRequest> books) {
        return bookService.save(books);
    }

    @Override
    protected boolean canExecute(List<BookRequest> books) {
        return books != null && !books.isEmpty();
    }
}
