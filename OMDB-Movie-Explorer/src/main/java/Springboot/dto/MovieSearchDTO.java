package Springboot.dto;


import java.util.List;

public class MovieSearchDTO {
    private List<MovieDTO> search;

    public List<MovieDTO> getSearch() { return search; }
    public void setSearch(List<MovieDTO> search) { this.search = search; }
}
