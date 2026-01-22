package com.finfactor.omdb.controller;

import com.finfactor.omdb.dto.MovieDetailResponse;
import com.finfactor.omdb.dto.MovieSearchResponse;
import com.finfactor.omdb.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/search")
    public ResponseEntity<MovieSearchResponse> searchMovies(@RequestParam String title) {
        MovieSearchResponse response = movieService.searchMovies(title);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<MovieDetailResponse> getMovieDetails(@PathVariable String imdbId) {
        MovieDetailResponse response = movieService.getMovieDetails(imdbId);
        return ResponseEntity.ok(response);
    }
}