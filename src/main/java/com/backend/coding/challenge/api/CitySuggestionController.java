package com.backend.coding.challenge.api;

import com.backend.coding.challenge.api.model.CitySuggestionResponse;
import com.backend.coding.challenge.sevice.suggestion.CitySuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/suggestions")
@RequiredArgsConstructor
public class CitySuggestionController {

    private final CitySuggestionService citySuggestionService;

    @GetMapping
    private ResponseEntity<CitySuggestionResponse> suggestCity(
            @RequestParam(name = "q") String cityName,
            @RequestParam(required = false) Double latitude,
            @RequestParam(required = false) Double longitude,
            @RequestParam(required = false, defaultValue = "5") Integer limit
    ) {
        return ResponseEntity.ok(
                new CitySuggestionResponse(
                        citySuggestionService.makeSuggestion(cityName, latitude, longitude, limit)
                )
        );
    }
}
