package Paddys.Patterns.BookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import Paddys.Patterns.BookStore.model.UserLogin;


@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{

}