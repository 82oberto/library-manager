package com.roberto.library_manager.command;

import com.roberto.library_manager.model.book.BookResponse;
import com.roberto.library_manager.service.BookEnrichmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class EnrichBookCommand extends GlobalCommand<Map.Entry<String, String>, BookResponse> {

    private final BookEnrichmentService enrichmentService;

    @Override
    protected BookResponse doExecute(Map.Entry<String, String> input) {
        return enrichmentService.enrich(input.getKey(), input.getValue());
    }

    @Override
    protected boolean canExecute(Map.Entry<String, String> input) {
        return input != null
                && input.getKey() != null && !input.getKey().isBlank()
                && input.getValue() != null && !input.getValue().isBlank();
    }
}