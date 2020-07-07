package pl.mateuszprzeczek.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import pl.mateuszprzeczek.domain.Pizza;
import pl.mateuszprzeczek.web.api.services.PizzaRestService;

@RestController
@RequestMapping(path = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class PizzaRestController {
	
	private PizzaRestService pizzaService;
	

	@Autowired
	public PizzaRestController(PizzaRestService pizzaService) {
		super();
		this.pizzaService = pizzaService;
	}

	
	@GetMapping
	public Iterable<Pizza> getPizzas() {
		return pizzaService.getPizzas();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Pizza postPizza(@RequestBody Pizza pizza) {
		return pizzaService.postPizza(pizza);
	}
	
	@GetMapping("/{id}")
	  public Pizza pizzaById(@PathVariable("id") Long id) {
	    return pizzaService.pizzaById(id);
	  }
	
	
	@GetMapping(path = "/sizes", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	public List<String> pizzasizes() {
		return pizzaService.pizzaSizes();
	}
	
	

}
