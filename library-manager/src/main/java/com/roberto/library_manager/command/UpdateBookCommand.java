package com.roberto.library_manager.command;

import com.roberto.library_manager.model.Book;
import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateBookCommand {

    private final BookService bookService;

    public Book doExecute(Long id, Book book) {
        return bookService.updateBook(id, book);
    }
}