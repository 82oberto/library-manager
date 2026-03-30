package com.roberto.library_manager.service;

import com.roberto.library_manager.exception.BookNotFoundException;
import com.roberto.library_manager.model.book.Book;
import com.roberto.library_manager.model.book.BookMapper;
import com.roberto.library_manager.model.book.BookResponse;
import com.roberto.library_manager.model.book.InsertBooksResult;
import com.roberto.library_manager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for book business logic.
 * Handles insertion with duplicate ISBN detection, retrieval, update and deletion.
 */

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookMapper mapper;

    public InsertBooksResult save(List<Book> books) {
        List<Book> toSave = new ArrayList<>();
        List<Book> duplicates = new ArrayList<>();

        for (Book book : books) {
            if (book.getIsbn() != null && repository.findByIsbn(book.getIsbn()).isPresent()) {
                duplicates.add(book);
            } else {
                toSave.add(book);
            }
        }

        List<Book> saved = repository.saveAll(toSave);
        return new InsertBooksResult(
                mapper.toResponseList(saved),
                mapper.toResponseList(duplicates));
    }

    public Page<BookResponse> getAll(Pageable pageable){
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public void deleteOne(Long id) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        repository.delete(existing);;
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public BookResponse update(Long id, Book book) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setIsbn(book.getIsbn());
        existing.setYear(book.getYear());
        existing.setGenre(book.getGenre());

        return mapper.toResponse(repository.save(existing));
    }
}
