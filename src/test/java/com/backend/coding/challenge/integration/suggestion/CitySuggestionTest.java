package com.backend.coding.challenge.integration.suggestion;

import com.backend.coding.challenge.integration.BaseIntegrationTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CitySuggestionTest extends BaseIntegrationTest {

    @Test
    @Sql("/sql/city.sql")
    @Sql(value = "/sql/city-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getSortedSuggestionsForRequestWithoutLongitude() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/suggestions").param("q", "lon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.suggestions").exists())
                .andExpect(jsonPath("$.suggestions", Matchers.hasSize(5)))
                .andExpect(jsonPath("$.suggestions[0].name").value("London, PPL, CA"))
                .andExpect(jsonPath("$.suggestions[0].score").value(0.5))
                .andExpect(jsonPath("$.suggestions[4].name").value("New London, PPL, US"))
                .andExpect(jsonPath("$.suggestions[4].score").value(0.3));

    }


    @Test
    @Sql("/sql/city.sql")
    @Sql(value = "/sql/city-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getSortedSuggestionsForRequestWithLongitude() throws Exception {

        mvc.perform(
                        MockMvcRequestBuilders.get("/suggestions")
                                .param("q", "london")
                                .param("latitude", "39.99645")
                                .param("longitude", "-83.74825")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.suggestions").exists())
                .andExpect(jsonPath("$.suggestions", Matchers.hasSize(5)))
                .andExpect(jsonPath("$.suggestions[0].name").value("London, PPLA2, US"))
                .andExpect(jsonPath("$.suggestions[0].latitude").value(39.88645))
                .andExpect(jsonPath("$.suggestions[0].longitude").value(-83.44825))
                .andExpect(jsonPath("$.suggestions[0].score").value(1.0));
    }

    @Test
    @Sql("/sql/city.sql")
    @Sql(value = "/sql/city-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void limitTest() throws Exception {

        mvc.perform(
                        MockMvcRequestBuilders.get("/suggestions")
                                .param("q", "london")
                                .param("latitude", "39.99645")
                                .param("longitude", "-83.74825")
                                .param("limit", "3")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.suggestions").exists())
                .andExpect(jsonPath("$.suggestions", Matchers.hasSize(3)))
                .andExpect(jsonPath("$.suggestions[0].name").value("London, PPLA2, US"))
                .andExpect(jsonPath("$.suggestions[0].score").value(1.0))
                //todo: improve name creation to avoid duplication
                .andExpect(jsonPath("$.suggestions[1].name").value("London, PPLA2, US"))
                .andExpect(jsonPath("$.suggestions[1].score").value(1.0))
                .andExpect(jsonPath("$.suggestions[2].name").value("London, PPL, CA"))
                .andExpect(jsonPath("$.suggestions[2].score").value(1.0));
    }

    @Test
    @Sql("/sql/city.sql")
    @Sql(value = "/sql/city-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void invalidQuery() throws Exception {

        mvc.perform(
                        MockMvcRequestBuilders.get("/suggestions")
                                .param("q", "")
                                .param("latitude", "39.99645")
                                .param("longitude", "-83.74825")
                ).andExpect(status().isBadRequest());
    }

    @Test
    @Sql("/sql/city.sql")
    @Sql(value = "/sql/city-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void invalidGeoData() throws Exception {

        mvc.perform(
                        MockMvcRequestBuilders.get("/suggestions")
                                .param("q", "lon")
                                .param("longitude", "-83.74825")
                ).andExpect(status().isBadRequest());
    }
    @Test
    @Sql("/sql/city.sql")
    @Sql(value = "/sql/city-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void invalidGeoDataFormat() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.get("/suggestions")
                                .param("q", "lon")
                                .param("longitude", "one and half")
                ).andExpect(status().isBadRequest());
    }
}
