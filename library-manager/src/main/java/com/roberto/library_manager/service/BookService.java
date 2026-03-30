package com.roberto.library_manager.service;

import com.roberto.library_manager.exception.BookNotFoundException;
import com.roberto.library_manager.model.book.Book;
import com.roberto.library_manager.model.book.BookMapper;
import com.roberto.library_manager.model.book.BookRequest;
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

    public InsertBooksResult save(List<BookRequest> requests) {
        List<Book> toSave = new ArrayList<>();
        List<Book> duplicates = new ArrayList<>();

        for (BookRequest request : requests) {
            if (request.getIsbn() != null && repository.findByIsbn(request.getIsbn()).isPresent()) {
                duplicates.add(mapper.toEntity(request));
            } else {
                toSave.add(mapper.toEntity(request));
            }
        }

        List<Book> saved = repository.saveAll(toSave);
        return new InsertBooksResult(
                mapper.toResponseList(saved),
                mapper.toResponseList(duplicates));
    }

    public Page<BookResponse> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public void deleteOne(Long id) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        repository.delete(existing);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public BookResponse update(Long id, BookRequest request) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        existing.setTitle(request.getTitle());
        existing.setAuthor(request.getAuthor());
        existing.setIsbn(request.getIsbn());
        existing.setYear(request.getYear());
        existing.setGenre(request.getGenre());

        return mapper.toResponse(repository.save(existing));
    }
}
