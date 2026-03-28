package com.roberto.library_manager.command;

import com.roberto.library_manager.model.Book;
import com.roberto.library_manager.model.BookResponse;
import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Command handler for retrieving the entire list of books.
 * Validates input and delegates to {@link BookService}.
 */
@Component
@RequiredArgsConstructor
public class GetAllBooksCommand extends GlobalCommand<Pageable, Page<BookResponse>> {

    private final BookService bookService;

    @Override
    public Page<BookResponse> doExecute(Pageable pageable){
        return bookService.getAllBooks(pageable);
    }

    @Override
    protected boolean canExecute(Pageable pageable) {
        return true;
    }

}
