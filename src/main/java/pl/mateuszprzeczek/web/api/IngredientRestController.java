package pl.mateuszprzeczek.web.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.mateuszprzeczek.domain.Ingredient;
import pl.mateuszprzeczek.web.api.services.IngredientRestService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/ingredients", 
	produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientRestController {
	
	private IngredientRestService ingredientService;
	
	
	@Autowired
	public IngredientRestController(IngredientRestService ingredientService) {
		super();
		this.ingredientService = ingredientService;
	}

	@GetMapping
	public Iterable<Ingredient> getIngredients() {
		return ingredientService.getIngredients();
	}
	
	@GetMapping("/{id}")
	  public Ingredient ingredientById(@PathVariable("id") String id) {
		return ingredientService.ingredientById(id);
	}

}
