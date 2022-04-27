package com.backend.coding.challenge.repository;

import com.backend.coding.challenge.repository.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Integer> {

    @Query(
            value = "select * from City c " +
                    "where c.name ilike CONCAT('%',:name,'%') " +
                    "or c.asciiName ilike CONCAT('%',:name,'%') " +
                    "or c.alternateNames ilike CONCAT('%',:name,'%')"
    )
    List<City> findCitiesWithSimilarName(String name);
}
