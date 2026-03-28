package com.roberto.library_manager.command;

import com.roberto.library_manager.model.Book;
import com.roberto.library_manager.model.InsertBooksResult;
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
public class InsertBooksCommand extends GlobalCommand<List<Book>, InsertBooksResult> {


    private final BookService bookService;

    @Override
    public InsertBooksResult doExecute(List<Book> books) {
        return bookService.insertBooks(books);
    }

    @Override
    protected boolean canExecute(List<Book> books) {
        return books != null && !books.isEmpty();
    }
}
