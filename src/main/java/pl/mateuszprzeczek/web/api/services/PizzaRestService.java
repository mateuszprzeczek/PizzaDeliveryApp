package pl.mateuszprzeczek.web.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import pl.mateuszprzeczek.domain.Pizza;
import pl.mateuszprzeczek.domain.Pizza.PizzaSize;
import pl.mateuszprzeczek.repository.PizzaRepository;

@Service
public class PizzaRestService {
	
	private PizzaRepository pizzaRepo;

	@Autowired
	public PizzaRestService(PizzaRepository pizzaRepo) {
		super();
		this.pizzaRepo = pizzaRepo;
	}
	
	public Iterable<Pizza> getPizzas() {
		return pizzaRepo.findAll();
	}
	
	public Pizza postPizza(Pizza pizza) {
		return pizzaRepo.save(pizza);
	}
	
	public Pizza pizzaById(@PathVariable("id") Long id) {
	    Optional<Pizza> optTaco = pizzaRepo.findById(id);
	    if (optTaco.isPresent()) {
	      return optTaco.get();
	    }
	    return null;
	  }
	
	public List<String> pizzasizes() {
		ArrayList<String> values = new ArrayList<>();
        for (PizzaSize size : PizzaSize.values()) {
                values.add(size.toString());
        }
        return values;
	}
	
	

}
