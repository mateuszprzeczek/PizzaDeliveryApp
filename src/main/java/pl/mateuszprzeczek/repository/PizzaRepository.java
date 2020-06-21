package pl.mateuszprzeczek.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.mateuszprzeczek.domain.Pizza;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {

}
