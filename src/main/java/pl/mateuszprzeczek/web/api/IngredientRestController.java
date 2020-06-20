package pl.mateuszprzeczek.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.mateuszprzeczek.domain.Ingredient;
import pl.mateuszprzeczek.repository.IngredientRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ingredients", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientRestController {
	
	private IngredientRepository ingredientRepo;
	
	
	@Autowired
	public IngredientRestController(IngredientRepository ingredientRepo) {
		this.ingredientRepo = ingredientRepo;
	}

	@GetMapping
	public Iterable<Ingredient> getIngredients() {
		return ingredientRepo.findAll();
	}
	
	@GetMapping("/{id}")
	  public Ingredient ingredientById(@PathVariable("id") Long id) {
	    Optional<Ingredient> optIngredient = ingredientRepo.findById(id);
	    if (optIngredient.isPresent()) {
	      return optIngredient.get();
	    }
	    return null;
	  }

}
