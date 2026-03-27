package com.roberto.library_manager.command;

import com.roberto.library_manager.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteOneBookCommand {

    private final BookService bookService;

    public void doExecute(Long id) {
        bookService.deleteOne(id);
    }
}