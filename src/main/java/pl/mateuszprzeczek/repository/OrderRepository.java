package pl.mateuszprzeczek.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.mateuszprzeczek.domain.Order;
import pl.mateuszprzeczek.domain.User;

@Repository
public interface OrderRepository 
         extends CrudRepository<Order, Long> {

  List<Order> findByUserOrderByPlacedAtDesc(
          User user, Pageable pageable);

}