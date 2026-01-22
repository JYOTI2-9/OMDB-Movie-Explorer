package Springboot.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class RatingDTO {

    @JsonProperty("Source")
    private String source;

    @JsonProperty("Value")
    private String value;
}
