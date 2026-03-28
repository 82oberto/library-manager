package com.roberto.library_manager.repository;

import com.roberto.library_manager.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * JPA repository for {@link Book} entity.
 * Provides standard CRUD operations and custom query for ISBN lookup.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
}