package com.backend.coding.challenge.sevice.suggestion;

import com.backend.coding.challenge.api.model.CitySuggestion;
import com.backend.coding.challenge.utils.geometry.Point;

import java.util.List;

public interface ScoreService {
    List<CitySuggestion> sortByScore(List<CitySuggestion> cities, String name, Integer limit);

    List<CitySuggestion> sortByScoreAndDistance(List<CitySuggestion> cities, String name, Point point, Integer limit);
}
