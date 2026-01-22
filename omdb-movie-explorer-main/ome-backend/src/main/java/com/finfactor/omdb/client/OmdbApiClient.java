package com.finfactor.omdb.client;

import com.finfactor.omdb.dto.MovieDetailResponse;
import com.finfactor.omdb.dto.MovieSearchResponse;
import com.finfactor.omdb.exception.OmdbApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Slf4j
@Component
@RequiredArgsConstructor
public class OmdbApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.base-url}")
    private String baseUrl;

    public MovieSearchResponse searchMovies(String title) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("apikey", apiKey)
                    .queryParam("s", title)
                    .toUriString();

            log.debug("Calling OMDB API: {}", url.replace(apiKey, "***"));

            MovieSearchResponse response = restTemplate.getForObject(url, MovieSearchResponse.class);

            if (response != null && "False".equalsIgnoreCase(response.getResponse())) {
                throw new OmdbApiException("Movie not found: " + title);
            }

            return response;
        } catch (Exception e) {
            log.error("Error calling OMDB API for search", e);
            throw new OmdbApiException("Failed to search movies", e);
        }
    }

    public MovieDetailResponse getMovieDetails(String imdbId) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                    .queryParam("apikey", apiKey)
                    .queryParam("i", imdbId)
                    .queryParam("plot", "full")
                    .toUriString();

            log.debug("Calling OMDB API: {}", url.replace(apiKey, "***"));

            MovieDetailResponse response = restTemplate.getForObject(url, MovieDetailResponse.class);

            if (response != null && "False".equalsIgnoreCase(response.getResponse())) {
                throw new OmdbApiException("Movie not found with ID: " + imdbId);
            }

            return response;
        } catch (Exception e) {
            log.error("Error calling OMDB API for details", e);
            throw new OmdbApiException("Failed to get movie details", e);
        }
    }
}
