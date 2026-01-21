package Springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Springboot.dao.MovieDAO;
import Springboot.dto.MovieDTO;
import Springboot.exceptionhandaling.MovieNotFoundException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class MovieService {

    private final MovieDAO movieDAO;
    private final ObjectMapper objectMapper;

    @Autowired
    public MovieService(MovieDAO movieDAO, ObjectMapper objectMapper) {
        this.movieDAO = movieDAO;
        this.objectMapper = objectMapper;
    }

    public MovieDTO[] searchMovies(String title) {
        try {
            String json = movieDAO.searchMovies(title);
            JsonNode node = objectMapper.readTree(json);

            if (node.has("Error")) {
                throw new MovieNotFoundException(title);
            }

            return objectMapper.treeToValue(node.get("Search"), MovieDTO[].class);
        } catch (Exception e) {
            throw new RuntimeException("Internal error", e);
        }
    }

    public MovieDTO getMovieById(String id) {
        try {
            String json = movieDAO.getMovieById(id);
            JsonNode node = objectMapper.readTree(json);

            if (node.has("Error")) {
                throw new MovieNotFoundException(id);
            }

            return objectMapper.treeToValue(node, MovieDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Internal error", e);
        }
    }
}
