package com.roberto.library_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * Entity representing a book in the library.
 * Contains title, author, ISBN, publication year and genre.
 */
@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    @Column(nullable = false)
    private String title;
    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author must not exceed 255 characters")
    @Column(nullable = false)
    private String author;
    @Column(unique = true)
    @Pattern(
            regexp = "^(?:\\d{9}[\\dX]|\\d{13})$",
            message = "Invalid ISBN format"
    )
    private String isbn;
    private int year;
    private String genre;
}
