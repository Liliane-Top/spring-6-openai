package nl.top.spring6aiintro.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record CapitalWithInfoResponse(@JsonPropertyDescription("The name of the city") String city,
                                      @JsonPropertyDescription("The population of the city") Integer population,
                                      @JsonPropertyDescription("The region the city is located in") String region,
                                      @JsonPropertyDescription("The primary langage spoken is") String language,
                                      @JsonPropertyDescription("the currency used") String currency) {
}
