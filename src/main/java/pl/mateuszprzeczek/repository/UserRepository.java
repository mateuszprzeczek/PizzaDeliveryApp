package pl.mateuszprzeczek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.mateuszprzeczek.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
  
  User findByEmail(String email);
  
}