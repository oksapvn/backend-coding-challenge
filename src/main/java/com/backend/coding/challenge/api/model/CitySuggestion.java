package com.backend.coding.challenge.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "name", "latitude", "longitude", "score" })
public class CitySuggestion {
    @JsonIgnore
    private String cityName;

    @JsonProperty("name")
    private String fullName;
    private Double latitude;
    private Double longitude;
    private Double score;
}
