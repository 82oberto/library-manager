package com.roberto.library_manager.command;

import com.roberto.library_manager.model.book.Book;
import com.roberto.library_manager.model.book.BookResponse;
import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Command handler for updating a book.
 * Validates input and delegates to {@link BookService}.
 */
@Component
@RequiredArgsConstructor
public class UpdateBookCommand extends GlobalCommand<Map.Entry<Long, Book>, BookResponse>{

    private final BookService bookService;

    @Override
    public BookResponse doExecute(Map.Entry<Long, Book> input) {

        return bookService.updateBook(input.getKey(), input.getValue());
    }

    @Override
    protected boolean canExecute(Map.Entry<Long, Book> input) {
        return input != null && input.getKey() != null && input.getKey() > 0 && input.getValue() != null;
    }
}