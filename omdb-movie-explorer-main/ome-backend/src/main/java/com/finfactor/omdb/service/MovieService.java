package com.finfactor.omdb.service;

import com.finfactor.omdb.client.OmdbApiClient;
import com.finfactor.omdb.dto.MovieDetailResponse;
import com.finfactor.omdb.dto.MovieSearchResponse;
import com.finfactor.omdb.exception.OmdbApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j // Lombok for logging
@Service
@RequiredArgsConstructor
public class MovieService {

    private final OmdbApiClient omdbApiClient;

    //  @Cacheable anno. to cache search results
    @Cacheable(value = "movieCache", key = "'search_' + #title")
    public MovieSearchResponse searchMovies(String title) {
        validateTitle(title);
        log.info("Fetching search results for: {}", title);
        return omdbApiClient.searchMovies(title);
    }

    @Cacheable(value = "movieCache", key = "'detail_' + #imdbId")
    public MovieDetailResponse getMovieDetails(String imdbId) {
        validateImdbId(imdbId);
        log.info("Fetching movie details for IMDB ID: {}", imdbId);
        return omdbApiClient.getMovieDetails(imdbId);
    }

    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new OmdbApiException("Movie title cannot be empty");
        }
        if (title.trim().length() < 2) {
            throw new OmdbApiException("Movie title must be at least 2 characters");
        }
    }

    private void validateImdbId(String imdbId) {
        if (imdbId == null || imdbId.trim().isEmpty()) {
            throw new OmdbApiException("IMDB ID cannot be empty");
        }
        if (!imdbId.matches("^tt\\d{7,8}$")) {
            throw new OmdbApiException("Invalid IMDB ID format. Expected format: tt followed by 7-8 digits (e.g., tt0372784)");
        }
    }
}