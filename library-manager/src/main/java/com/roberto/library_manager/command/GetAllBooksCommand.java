package com.roberto.library_manager.command;

import com.roberto.library_manager.model.Book;
import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Command handler for retrieving the entire list of books.
 * Validates input and delegates to {@link BookService}.
 */
@Component
@RequiredArgsConstructor
public class GetAllBooksCommand extends GlobalCommand<Void, List<Book>> {

    private final BookService bookService;

    @Override
    public List<Book> doExecute(Void input){
        return bookService.getAllBooks();
    }

    @Override
    protected boolean canExecute(Void input) {
        return true;
    }

}
