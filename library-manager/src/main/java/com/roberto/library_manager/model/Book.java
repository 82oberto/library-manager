package com.roberto.library_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String title;
    @NotNull
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
