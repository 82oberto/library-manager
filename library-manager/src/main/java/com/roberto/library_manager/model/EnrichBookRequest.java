package com.roberto.library_manager.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnrichBookRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;
}