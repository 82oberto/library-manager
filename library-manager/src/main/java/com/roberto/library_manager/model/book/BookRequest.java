package com.roberto.library_manager.model.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * DTO representing the input for creating or updating a book.
 * Does not expose internal fields like id.
 */
@Data
public class BookRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author must not exceed 255 characters")
    private String author;

    @Pattern(
            regexp = "^(?:\\d{9}[\\dX]|\\d{13})$",
            message = "Invalid ISBN format"
    )
    private String isbn;

    private int year;
    private String genre;
}
