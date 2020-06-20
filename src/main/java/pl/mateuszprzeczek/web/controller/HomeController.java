package pl.mateuszprzeczek.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.mateuszprzeczek.domain.Ingredient;
import pl.mateuszprzeczek.domain.Order;
import pl.mateuszprzeczek.domain.Pizza;
import pl.mateuszprzeczek.repository.IngredientRepository;
import pl.mateuszprzeczek.repository.PizzaRepository;

@Controller
@RequestMapping("/home")
@SessionAttributes("order")
public class HomeController {
	
	private IngredientRepository ingredientRepo;
	private PizzaRepository pizzaRepo;
	
	@Autowired
	public HomeController(IngredientRepository ingredientRepo, PizzaRepository pizzaRepo) {
		super();
		this.ingredientRepo = ingredientRepo;
		this.pizzaRepo = pizzaRepo;
	}




	@GetMapping()
	public String home(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		
		Ingredient.Type[] types = Ingredient.Type.values();
		for(Ingredient.Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), 
					filterByType(ingredients, type));
		model.addAttribute("pizzaModel", new Pizza());
		model.addAttribute("order", new Order());
		
			}
		return "home";	
		}
	
	@PostMapping()
	public String postPizza(Pizza pizza, @ModelAttribute Order order) {
		Pizza saved = pizzaRepo.save(pizza);
		order.addOrder(saved);
		return "redirect:/orders/current";
	}
	
	
	
	
	
	private List<Ingredient> filterByType(
		      List<Ingredient> ingredients, Ingredient.Type type) {
		    return ingredients
		              .stream()
		              .filter(ingredient -> ingredient.getType().equals(type))
		              .collect(Collectors.toList());
		  }
	
	
	
}
