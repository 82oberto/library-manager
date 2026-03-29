package com.roberto.library_manager.service;

import com.roberto.library_manager.exception.BookNotFoundException;
import com.roberto.library_manager.model.Book;
import com.roberto.library_manager.model.BookMapper;
import com.roberto.library_manager.model.BookResponse;
import com.roberto.library_manager.model.OpenLibraryResponse;
import com.roberto.library_manager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookEnrichmentService {
    private final BookMapper mapper;

    private final WebClient webClient = WebClient.create("https://openlibrary.org");
    private final BookRepository bookRepository;

    public BookResponse enrich(String title, String author) {
        log.info("Enriching book: {} by {}", title, author);

        OpenLibraryResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search.json")
                        .queryParam("title", replaceSpace(title))
                        .queryParam("author", replaceSpace(author))
                        .queryParam("limit", 1)
                        .build())
                .retrieve()
                .bodyToMono(OpenLibraryResponse.class)
                .block();
        log.info("response: {}", response);

        if (response == null || response.getDocs() == null || response.getDocs().isEmpty()) {
            throw new BookNotFoundException("No book found for title: " + title + " and author: " + author);
        }

        OpenLibraryResponse.OpenLibraryBook found = response.getDocs().getFirst();

        Book book = new Book();
        book.setTitle(found.getTitle());
        book.setAuthor(String.join(", ", found.getAuthor_name()));

        if (found.getIsbn() != null && !found.getIsbn().isEmpty()) {
            book.setIsbn(found.getIsbn().getFirst());
        }
        if (found.getFirst_publish_year() != null) {
            book.setYear(found.getFirst_publish_year());
        }
        if (found.getSubject() != null && !found.getSubject().isEmpty()) {
            book.setGenre(found.getSubject().getFirst());
        }
        bookRepository.save(book);
        log.info("Book enriched and saved successfully: {}, id: {}", book.getTitle(), book.getId());
        return mapper.toResponse(book);
    }

    private  String replaceSpace(String input){
        return input.replaceAll("\\s+", "_");
    }
}