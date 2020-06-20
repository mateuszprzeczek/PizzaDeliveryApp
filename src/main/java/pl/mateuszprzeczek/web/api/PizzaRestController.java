package pl.mateuszprzeczek.web.api;

import java.util.Optional;

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
import pl.mateuszprzeczek.repository.PizzaRepository;

@RestController
@RequestMapping(path = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class PizzaRestController {
	
	private PizzaRepository pizzaRepo;

	@Autowired
	public PizzaRestController(PizzaRepository pizzaRepo) {
		this.pizzaRepo = pizzaRepo;
	}
	
	@GetMapping
	public Iterable<Pizza> getPizzas() {
		return pizzaRepo.findAll();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public Pizza postPizza(@RequestBody Pizza pizza) {
		return pizzaRepo.save(pizza);
	}
	
	@GetMapping("/{id}")
	  public Pizza pizzaById(@PathVariable("id") Long id) {
	    Optional<Pizza> optTaco = pizzaRepo.findById(id);
	    if (optTaco.isPresent()) {
	      return optTaco.get();
	    }
	    return null;
	  }
	
	

}
