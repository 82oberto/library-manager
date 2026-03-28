package com.roberto.library_manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InsertBooksResult {
    private List<Book> saved;
    private List<Book> duplicates;
}
