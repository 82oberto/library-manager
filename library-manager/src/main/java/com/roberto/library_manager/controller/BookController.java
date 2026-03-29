package com.roberto.library_manager.controller;

import com.roberto.library_manager.command.*;
import com.roberto.library_manager.model.book.Book;
import com.roberto.library_manager.model.book.BookResponse;
import com.roberto.library_manager.model.book.EnrichBookRequest;
import com.roberto.library_manager.model.book.InsertBooksResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private final EnrichBookCommand enrichBookCommand;



    @Operation(
            summary = "Insert a list of books",
            description = "Inserts one or multiple books at once. Returns saved books and duplicates based on ISBN."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Books processed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<InsertBooksResult> insertBooks(@Valid @RequestBody List<Book> books){
        InsertBooksResult booksResult = insertBooksCommand.execute(books);
        return ResponseEntity.ok(booksResult);
    }

    @Operation(summary = "Get all books", description = "Returns the complete list of books in the library.")
    @ApiResponse(responseCode = "200", description = "Books retrieved successfully")
    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<BookResponse> books = getAllBooksCommand.execute(pageable);
        return ResponseEntity.ok(books);
    }

    @Operation(summary = "Update a book", description = "Updates all fields of an existing book by id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody Book book) {
        Map.Entry<Long, Book> input = Map.entry(id, book);
        BookResponse updated = updateBookCommand.execute(input);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete a book by id", description = "Deletes a single book by its id.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Long id) {
        deleteOneCommand.execute(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete all books", description = "Deletes all books from the library.")
    @ApiResponse(responseCode = "204", description = "All books deleted successfully")
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        deleteAllCommand.execute(null);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Enrich a book", description = "Finds book details from Open Library by title and author and save it.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book enriched successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found on Open Library")
    })
    @PostMapping("/enrich")
    public ResponseEntity<BookResponse> enrichBook(@Valid @RequestBody EnrichBookRequest request) {
        BookResponse book = enrichBookCommand.execute(Map.entry(request.getTitle(), request.getAuthor()));
        return ResponseEntity.ok(book);
    }
}
