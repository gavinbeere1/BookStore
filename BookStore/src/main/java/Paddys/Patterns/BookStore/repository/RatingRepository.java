package Paddys.Patterns.BookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Paddys.Patterns.BookStore.model.Rating;



@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>{

}
