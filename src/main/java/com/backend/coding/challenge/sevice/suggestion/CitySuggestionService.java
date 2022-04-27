package com.backend.coding.challenge.sevice.suggestion;

import com.backend.coding.challenge.api.model.CitySuggestion;

import java.util.List;

public interface CitySuggestionService {
    List<CitySuggestion> makeSuggestion(
            String name,
            Double latitude,
            Double longitude,
            Integer limit
    );
}
