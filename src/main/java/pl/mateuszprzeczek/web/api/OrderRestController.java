package pl.mateuszprzeczek.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.mateuszprzeczek.domain.Order;
import pl.mateuszprzeczek.repository.OrderRepository;

@RestController
@RequestMapping(path = "orders", 
				produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class OrderRestController {
	
	private OrderRepository orderRepo;

	@Autowired
	public OrderRestController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping(produces="application/json")
	  public Iterable<Order> allOrders() {
	    return orderRepo.findAll();
	  }
	
	@PostMapping(consumes="application/json")
	  @ResponseStatus(HttpStatus.CREATED)
	  public Order postOrder(@RequestBody Order order) {
	    return orderRepo.save(order);
	  }
	
	

}
