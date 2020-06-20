package pl.mateuszprzeczek.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.mateuszprzeczek.domain.Pizza;
import pl.mateuszprzeczek.repository.PizzaRepository;

@Controller
@RequestMapping("/menu/pizzas")
public class PizzaController {
	
	private PizzaRepository pizzaRepo;
	
	
	
	public PizzaController(PizzaRepository pizzaRepo) {
		super();
		this.pizzaRepo = pizzaRepo;
	}


	@GetMapping
	public String getPizzas(Model model) {
		Iterable<Pizza> pizzas = pizzaRepo.findAll();
		model.addAttribute("pizzasList", pizzas);
		return "pizzas";
	}
	
	@PostMapping
	public String postPizza(@ModelAttribute Pizza pizza, RedirectAttributes redirectAttributes) {
		pizza.setCreatedAt(new Date());
		pizzaRepo.save(pizza);
		redirectAttributes.addFlashAttribute("message", "Pizza added successfuly");
		return "redirect:/";
	}

}
