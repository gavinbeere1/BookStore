package Paddys.Patterns.BookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Paddys.Patterns.BookStore.model.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
