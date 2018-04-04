package Paddys.Patterns.BookStore.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Paddys.Patterns.BookStore.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

	ArrayList<Book> findAll();

	Book findByTitle(String title);

}
