package com.backend.coding.challenge.sevice.suggestion.impl;

import com.backend.coding.challenge.api.model.CitySuggestion;
import com.backend.coding.challenge.sevice.suggestion.ScoreService;
import com.backend.coding.challenge.sevice.text.StringSimilarityService;
import com.backend.coding.challenge.utils.geometry.Point;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingDouble;

@Slf4j
@Service
@AllArgsConstructor
public class ScoreServiceImpl implements ScoreService {

    private final StringSimilarityService stringSimilarityService;

    @Override
    public List<CitySuggestion> sortByScore(List<CitySuggestion> cities,
                                            String name,
                                            Integer limit) {

        return sort(cities, name, null, limit);
    }

    @Override
    public List<CitySuggestion> sortByScoreAndDistance(
            List<CitySuggestion> cities,
            String name,
            Point point,
            Integer limit) {

        return sort(cities, name, point, limit);
    }

    private List<CitySuggestion> sort(List<CitySuggestion> cities, String name, Point point, Integer limit) {

        return cities.stream()
                .peek(city -> city.setScore(stringSimilarityService.scoreSimilarity(city.getCityName(), name)))
                .sorted(buildComparator(point))
                .limit(limit)
                .collect(Collectors.toList());
    }

    private Comparator<CitySuggestion> buildComparator(Point point) {
        if (point == null) {
            log.warn("Geo position isn't provided. Result will be sorted by score");
            return comparingDouble(CitySuggestion::getScore).reversed();
        } else {
            return comparingDouble(CitySuggestion::getScore).reversed()
                    .thenComparing(
                            (leftCity, rightCity) -> {
                                Double distance = point.calculateDistance(new Point(leftCity.getLatitude(), leftCity.getLongitude()));
                                return distance.compareTo(
                                        point.calculateDistance(new Point(rightCity.getLatitude(), rightCity.getLongitude()))
                                );
                            }
                    );
        }

    }
}
