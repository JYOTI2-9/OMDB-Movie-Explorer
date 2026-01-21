package Springboot.entity;




import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorite_movies")
public class FavoriteMovie {

    @Id
    private String imdbID;
    private String title;
    private String year;
    private String poster;

    
    
    public String getImdbID() { return imdbID; }
    public void setImdbID(String imdbID) { this.imdbID = imdbID; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getPoster() { return poster; }
    public void setPoster(String poster) { this.poster = poster; }
}
