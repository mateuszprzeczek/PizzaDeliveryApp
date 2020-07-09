package pl.mateuszprzeczek.web.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import pl.mateuszprzeczek.domain.Ingredient;
import pl.mateuszprzeczek.repository.IngredientRepository;

@Service
public class IngredientRestService {
	
	private IngredientRepository ingredientRepo;

	@Autowired
	public IngredientRestService(IngredientRepository ingredientRepo) {
		super();
		this.ingredientRepo = ingredientRepo;
	}
	
	public Iterable<Ingredient> getIngredients() {
		return ingredientRepo.findAll();
	}
	
	public Ingredient ingredientById(@PathVariable("id") String id) {
	    Optional<Ingredient> optIngredient = ingredientRepo.findById(id);
	    if (optIngredient.isPresent()) {
	      return optIngredient.get();
	    }
	    return null;
	  }
	
	
	

}
