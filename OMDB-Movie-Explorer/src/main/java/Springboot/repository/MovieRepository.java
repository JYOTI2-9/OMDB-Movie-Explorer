package Springboot.repository;




import org.springframework.data.jpa.repository.JpaRepository;

import Springboot.entity.FavoriteMovie;

public interface MovieRepository extends JpaRepository<FavoriteMovie, String> {
}
