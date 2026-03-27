package com.roberto.library_manager.command;

import com.roberto.library_manager.model.Book;
import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllBooksCommand {

    private final BookService bookService;

    public List<Book> doExecute(){
        return bookService.getAllBooks();
    }

}
