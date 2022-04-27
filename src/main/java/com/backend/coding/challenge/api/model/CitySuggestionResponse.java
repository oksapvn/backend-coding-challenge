package com.backend.coding.challenge.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CitySuggestionResponse {
    private List<CitySuggestion> suggestions;
}
