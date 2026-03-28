package com.roberto.library_manager.controller;

import com.roberto.library_manager.command.*;
import com.roberto.library_manager.model.Book;
import com.roberto.library_manager.model.InsertBooksResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<InsertBooksResult> insertBooks(@Valid @RequestBody List<Book> books){
        InsertBooksResult booksResult = insertBooksCommand.execute(books);
        return ResponseEntity.ok(booksResult);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = getAllBooksCommand.execute(null);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        Map.Entry<Long, Book> input = Map.entry(id, book);
        Book updated = updateBookCommand.execute(input);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        deleteOneCommand.execute(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        deleteAllCommand.execute(null);
        return ResponseEntity.noContent().build();
    }
}
