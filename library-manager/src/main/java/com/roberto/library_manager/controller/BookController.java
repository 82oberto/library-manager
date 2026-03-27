package com.roberto.library_manager.controller;

import com.roberto.library_manager.command.*;
import com.roberto.library_manager.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing books.
 * Provides endpoints for creating, retrieving, updating and deleting books.
 */
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final InsertBooksCommand insertBooksCommand;
    private final GetAllBooksCommand getAllBooksCommand;
    private final DeleteOneBookCommand deleteOneCommand;
    private final DeleteAllBooksCommand deleteAllCommand;
    private final UpdateBookCommand updateBookCommand;


    @PostMapping
    public ResponseEntity<List<Book>> insertBooks(@RequestBody List<Book> books){
        List<Book> booksResult = insertBooksCommand.doExecute(books);
        return ResponseEntity.ok(booksResult);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = getAllBooksCommand.doExecute();
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updated = updateBookCommand.doExecute(id, book);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        deleteOneCommand.doExecute(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        deleteAllCommand.doExecute();
        return ResponseEntity.noContent().build();
    }
}
