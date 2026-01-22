package Springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Springboot.dto.MovieDTO;
import Springboot.service.MovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // 🔍 Search movies by title
    // GET http://localhost:8080/api/movies/search?title=Avengers
    @GetMapping("/search")
    public MovieDTO[] searchMovies(@RequestParam String title) {
        return movieService.searchMovies(title);
    }

    // 🎬 Get movie details by IMDB ID
    // GET http://localhost:8080/api/movies/tt0848228
    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id);
    }
}
