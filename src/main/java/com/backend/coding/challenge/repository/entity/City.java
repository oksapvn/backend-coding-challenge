package com.backend.coding.challenge.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@Entity
@Table(name = "city")
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @Column(name = "geonameid")
    private Integer geonameId;
    @Column(name = "name")
    private String name;
    @Column(name = "asciiname")
    private String asciiName;
    @Column(name = "alternatenames")
    private String alternateNames;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "country_code")
    private String countryCode;
    @Column(name = "feature_code")
    private String featureCode;
}
