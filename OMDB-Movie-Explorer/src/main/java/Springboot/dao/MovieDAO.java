package Springboot.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class MovieDAO {

    private final RestTemplate restTemplate;

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.url}")
    private String apiUrl;

    public MovieDAO(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Search movies
    public String searchMovies(String title) {
        String url = String.format("%s?apikey=%s&s=%s", apiUrl, apiKey, title);
        return restTemplate.getForObject(url, String.class);
    }

    // Get movie by ID
    public String getMovieById(String id) {
        String url = String.format("%s?apikey=%s&i=%s", apiUrl, apiKey, id);
        return restTemplate.getForObject(url, String.class);
    }
}
