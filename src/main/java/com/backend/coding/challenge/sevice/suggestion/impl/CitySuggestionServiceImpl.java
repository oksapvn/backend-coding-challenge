package com.backend.coding.challenge.sevice.suggestion.impl;

import com.backend.coding.challenge.api.model.CitySuggestion;
import com.backend.coding.challenge.exception.ResponseHttpException;
import com.backend.coding.challenge.mapper.CityMapper;
import com.backend.coding.challenge.repository.CityRepository;
import com.backend.coding.challenge.repository.entity.City;
import com.backend.coding.challenge.sevice.suggestion.CitySuggestionService;
import com.backend.coding.challenge.sevice.suggestion.ScoreService;
import com.backend.coding.challenge.utils.geometry.Point;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitySuggestionServiceImpl implements CitySuggestionService {

    private final CityMapper cityMapper;
    private final CityRepository cityRepository;
    private final ScoreService scoreService;

    public List<CitySuggestion> makeSuggestion(String name, Double latitude, Double longitude, Integer limit) {

        validateParameters(name, latitude, longitude);

        List<City> citiesWithSimilarName = cityRepository.findCitiesWithSimilarName(name);
        List<CitySuggestion> unscoredCitySuggestions = cityMapper.mapToSuggestionModel(citiesWithSimilarName);

        if (latitude != null && longitude != null) {
            return scoreService.sortByScoreAndDistance(unscoredCitySuggestions, name, new Point(latitude, longitude), limit);
        } else {
            return scoreService.sortByScore(unscoredCitySuggestions, name, limit);
        }
    }

    private void validateParameters(String name, Double latitude, Double longitude) {
        if (name == null || name.isBlank()) {
            throw new ResponseHttpException(HttpStatus.BAD_REQUEST, "Query cannot be null or empty");
        }

        if ((latitude == null && longitude != null)) {
            throw new ResponseHttpException(HttpStatus.BAD_REQUEST, "You need to set both or neither latitude and longitude");
        }

        if ((longitude == null && latitude != null)) {
            throw new ResponseHttpException(HttpStatus.BAD_REQUEST, "You need to set both or neither latitude and longitude");
        }
    }
}
