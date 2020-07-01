package pl.mateuszprzeczek.web.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mateuszprzeczek.domain.Order;
import pl.mateuszprzeczek.repository.OrderRepository;

@Service
public class OrderRestService {

	private OrderRepository orderRepository;

	
	@Autowired
	public OrderRestService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}
	
	public Iterable<Order> allOrders() {
	    return orderRepository.findAll();
	  }
	
	public Order postOrder(Order order) {
	    return orderRepository.save(order);
	  }
	
}
