package pl.mateuszprzeczek.web.api.services;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mateuszprzeczek.domain.Ingredient;
import pl.mateuszprzeczek.domain.Pizza;
import pl.mateuszprzeczek.repository.PizzaRepository;

@ExtendWith(MockitoExtension.class)
class PizzaRestServiceTest {
	
	@InjectMocks
	PizzaRestService pizzaRestService;
	@Mock
	PizzaRepository pizzaRepository;
	

	@Test
	void getPizzasShouldReturnNotNullValue() {
		
		//given
		Iterable<Pizza> pizzas = preparePizzasData();
		given(pizzaRepository.findAll()).willReturn(pizzas);
		
		//when
		Iterable<Pizza> pizzasIterable = pizzaRestService.getPizzas();
		
		//then
		assertThat(pizzasIterable.iterator().next().getName(), notNullValue());
	}
	
	private Iterable<Pizza> preparePizzasData() {
		
		Ingredient thinCake = new Ingredient("THIN", "ciasto cienkie");
        thinCake.setType(Ingredient.Type.WRAP);
		
		Ingredient salami = new Ingredient("SALA", "salami");
        salami.setType(Ingredient.Type.PROTEIN);
        
        Pizza pizza1 = new Pizza();
        pizza1.setName("Mozarella");
        pizza1.setIngredients(Arrays.asList(thinCake));
        pizza1.setPrice(18);

        Pizza pizza2 = new Pizza();
        pizza2.setName("Salami");
        pizza2.setIngredients(Arrays.asList(salami, thinCake));
        pizza2.setPrice(20);
        
        
		return Arrays.asList(pizza1, pizza2);
	}
	
	@Test
	void testPostPizza() {
		//given
		Pizza pizza = createPizzaForPostPizza();
		doReturn(pizza).when(pizzaRepository).save(pizza);
		
		//when
		Pizza returnedPizza = pizzaRestService.postPizza(pizza);
		
		
		assertThat(returnedPizza.getName(), containsString("ella"));
		assertThat(returnedPizza.getPrice(), equalTo(18.0));
	}
	
	
	private Pizza createPizzaForPostPizza() {
		Ingredient thinCake = new Ingredient("THIN", "ciasto cienkie");
        thinCake.setType(Ingredient.Type.WRAP);
		
		Ingredient salami = new Ingredient("SALA", "salami");
        salami.setType(Ingredient.Type.PROTEIN);
        
        Pizza pizza1 = new Pizza();
        pizza1.setName("Mozarella");
        pizza1.setIngredients(Arrays.asList(thinCake));
        pizza1.setPrice(18);
        
        
		return pizza1;
	}
	
	@Test
	void testGetPizzaByIdShouldNotBeEmpty() {
		
		//given
		Ingredient thinCake = new Ingredient("THIN", "ciasto cienkie");
        thinCake.setType(Ingredient.Type.WRAP);
		Pizza pizza = new Pizza(1L, "Salami", new Date(), Arrays.asList(thinCake));
        doReturn(Optional.of(pizza)).when(pizzaRepository).findById(1l);

        //when
        Pizza returnedPizza = pizzaRestService.pizzaById(1l);

        //then
        assertThat(returnedPizza, notNullValue());
        
	}
	
	@Test
	void testGetPizzaByIdShouldReturnNullValue() {
		
		//given
		//when
		Pizza returnedPizza = pizzaRestService.pizzaById(1L);
		
		//then
		assertThat(returnedPizza, nullValue());
	}
	
	@Test
	void testPizzaSizes() {
		
		//given
		List<String> sizes = Arrays.asList("32cm", "40cm", "50cm", "60cm");
		PizzaRestService pizzaRestService = mock(PizzaRestService.class);
		
		given(pizzaRestService.pizzaSizes()).willReturn(sizes);
		
		//when
		List<String> sizesList = pizzaRestService.pizzaSizes();
		
		//then
		assertThat(sizesList, notNullValue());
	}
	
	
	
	
	
	
	
	
	
	

	

}
