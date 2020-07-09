package pl.mateuszprzeczek.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.mateuszprzeczek.domain.Order;
import pl.mateuszprzeczek.web.api.services.OrderRestService;

@RestController
@RequestMapping(path = "orders", 
				produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class OrderRestController {
	
	private OrderRestService orderService;
	
	
	@Autowired
	public OrderRestController(OrderRestService orderService) {
		super();
		this.orderService = orderService;
	}

	@GetMapping(produces="application/json")
	  public Iterable<Order> allOrders() {
	    return orderService.allOrders();
	  }
	
	@PostMapping(consumes="application/json")
	  @ResponseStatus(HttpStatus.CREATED)
	  public Order postOrder(@RequestBody Order order) {
	    return orderService.postOrder(order);
	  }
	
	

}
