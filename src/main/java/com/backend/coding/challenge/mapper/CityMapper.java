package com.backend.coding.challenge.mapper;

import com.backend.coding.challenge.api.model.CitySuggestion;
import com.backend.coding.challenge.repository.entity.City;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityMapper {

    public List<CitySuggestion> mapToSuggestionModel(Collection<City> cities) {
        return cities.stream()
                .map(this::mapToSuggestionModel)
                .collect(Collectors.toList());
    }

    public CitySuggestion mapToSuggestionModel(City city) {
        return CitySuggestion.builder()
                .cityName(city.getName())
                .fullName(String.join(", ", city.getName(), city.getFeatureCode(), city.getCountryCode()))
                .latitude(city.getLatitude())
                .longitude(city.getLongitude())
                .score(0.0)
                .build();
    }

}
