package com.roberto.library_manager.service;

import com.roberto.library_manager.model.Book;
import com.roberto.library_manager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public List<Book> insertBooks(List<Book> books){
        return repository.saveAll(books);
    }

    public List<Book> getAllBooks(){
        return repository.findAll();
    }

    public void deleteOne(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Book updateBook(Long id, Book book) {
        Book existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        existing.setIsbn(book.getIsbn());
        existing.setYear(book.getYear());
        existing.setGenre(book.getGenre());

        return repository.save(existing);
    }
}
