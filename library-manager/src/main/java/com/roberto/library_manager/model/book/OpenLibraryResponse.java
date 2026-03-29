package com.roberto.library_manager.model.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibraryResponse {

    private List<OpenLibraryBook> docs;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class OpenLibraryBook {
        private String title;
        private List<String> author_name;
        private List<String> isbn;
        private Integer first_publish_year;
        private List<String> subject;
    }
}