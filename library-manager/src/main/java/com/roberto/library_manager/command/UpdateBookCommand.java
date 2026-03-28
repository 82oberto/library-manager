package com.roberto.library_manager.command;

import com.roberto.library_manager.model.Book;
import com.roberto.library_manager.model.InsertBooksResult;
import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UpdateBookCommand extends GlobalCommand<Map.Entry<Long, Book>, Book>{

    private final BookService bookService;

    @Override
    public Book doExecute(Map.Entry<Long, Book> input) {

        return bookService.updateBook(input.getKey(), input.getValue());
    }

    @Override
    protected boolean canExecute(Map.Entry<Long, Book> input) {
        return input != null && input.getKey() != null && input.getKey() > 0 && input.getValue() != null;
    }
}