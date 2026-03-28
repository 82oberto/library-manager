package com.roberto.library_manager.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
/**
 * Result object returned after a bulk book insertion.
 * Contains the list of successfully saved books and the list of duplicates
 * that were skipped due to existing ISBN in the database.
 */
@Data
@AllArgsConstructor
public class InsertBooksResult {
    private List<Book> saved;
    private List<Book> duplicates;
}
