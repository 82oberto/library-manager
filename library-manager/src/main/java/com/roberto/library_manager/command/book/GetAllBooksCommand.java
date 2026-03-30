package com.roberto.library_manager.command.book;

import com.roberto.library_manager.command.GlobalCommand;
import com.roberto.library_manager.model.book.BookResponse;
import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

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
        return bookService.getAll(pageable);
    }

    @Override
    protected boolean canExecute(Pageable pageable) {
        return true;
    }

}
