package pl.mateuszprzeczek.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pl.mateuszprzeczek.domain.Order;
import pl.mateuszprzeczek.repository.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;

	@Autowired
	public OrderController(OrderRepository orderRepo) {
		super();
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String orderProcess(Order order, SessionStatus sessionStatus) {
		orderRepo.save(order);
		return "redirect:/";
	}
	
	

}
