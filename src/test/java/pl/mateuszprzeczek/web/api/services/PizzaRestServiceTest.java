package pl.mateuszprzeczek.web.api.services;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import pl.mateuszprzeczek.domain.Pizza.PizzaSize;
import pl.mateuszprzeczek.repository.PizzaRepository;

@ExtendWith(MockitoExtension.class)
class PizzaRestServiceTest {

	@InjectMocks
	PizzaRestService pizzaRestService;
	@Mock
	PizzaRepository pizzaRepository;

	@Test
	void getPizzasShouldReturnNotNullValue() {

		// given
		Iterable<Pizza> pizzas = preparePizzasData();
		given(pizzaRepository.findAll()).willReturn(pizzas);

		// when
		Iterable<Pizza> pizzasIterable = pizzaRestService.getPizzas();

		// then
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
		// given
		Pizza pizza = createPizzaForPostPizza();
		doReturn(pizza).when(pizzaRepository).save(pizza);

		// when
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

		// given
		Ingredient thinCake = new Ingredient("THIN", "ciasto cienkie");
		thinCake.setType(Ingredient.Type.WRAP);
		Pizza pizza = new Pizza(1L, "Salami", new Date(), Arrays.asList(thinCake));
		doReturn(Optional.of(pizza)).when(pizzaRepository).findById(1l);

		// when
		Pizza returnedPizza = pizzaRestService.pizzaById(1l);

		// then
		assertThat(returnedPizza, notNullValue());

	}

	@Test
	void testGetPizzaByIdShouldReturnNullValue() {

		// given
		// when
		Pizza returnedPizza = pizzaRestService.pizzaById(1L);

		// then
		assertThat(returnedPizza, nullValue());
	}

	@Test
	void testPizzaSizesShouldReturnNotNullValue() {

		// given
		List<String> sizes = Arrays.asList("SMALL", "MEDIUM", "LARGE", "XLARGE");
		PizzaRestService pizzaRestService = mock(PizzaRestService.class);
		given(pizzaRestService.pizzaSizes()).willReturn(sizes);

		// when
		List<String> sizesList = pizzaRestService.pizzaSizes();
		ArrayList<String> values = new ArrayList<>();
		for (PizzaSize size : PizzaSize.values()) {
			values.add(size.toString());
		}

		// then
		assertThat(sizesList, notNullValue());
		verify(pizzaRestService).pizzaSizes();
		then(pizzaRestService).should().pizzaSizes();
		assertEquals("SMALL", pizzaRestService.pizzaSizes().get(0));
		assertEquals("MEDIUM", pizzaRestService.pizzaSizes().get(1));
		assertEquals("LARGE", pizzaRestService.pizzaSizes().get(2));
		assertEquals("XLARGE", pizzaRestService.pizzaSizes().get(3));
		assertEquals(sizesList, values);
		assertThat(sizesList.get(0), equalTo(PizzaSize.SMALL.toString()));
		assertThat(sizesList.size(), equalTo(4));
	}

	@Test
	void pizzaSizesShouldReturnValuesEqualToPizzaSizesEnum() {
		// given
		ArrayList<String> values = new ArrayList<>();

		// when
		for (PizzaSize size : PizzaSize.values()) {
			values.add(size.toString());
		}

		assertThat(values.get(0), equalTo(PizzaSize.SMALL.toString()));
		assertThat(values.get(1), equalTo(PizzaSize.MEDIUM.toString()));
		assertThat(values.get(2), equalTo(PizzaSize.LARGE.toString()));
		assertThat(values.get(3), equalTo(PizzaSize.XLARGE.toString()));
	}

	@Test
	void testPizzaSizesLoopShouldReturnNotNullValues() {

		// given
		List<String> sizes = Arrays.asList("32cm", "40cm", "50cm", "60cm");
		PizzaRestService pizzaRestService = mock(PizzaRestService.class);

		given(pizzaRestService.pizzaSizes()).willReturn(sizes);

		// when
		List<String> sizesList = pizzaRestService.pizzaSizes();
		List<String> returnedList = new ArrayList<>();
		for (String size : sizesList) {
			returnedList.add(size);
		}

		// then
		assertThat(returnedList, notNullValue());

		assertEquals(returnedList.get(0), pizzaRestService.pizzaSizes().get(0));
		assertEquals(returnedList.get(1), pizzaRestService.pizzaSizes().get(1));
		assertEquals(returnedList.get(2), pizzaRestService.pizzaSizes().get(2));
		assertEquals(returnedList.get(3), pizzaRestService.pizzaSizes().get(3));
	}

	@Test
	void testPizzaSizes() {

		// given
		List<String> sizes = Arrays.asList("32cm", "40cm", "50cm", "60cm");
		PizzaRestService pizzaRestService = mock(PizzaRestService.class);

		// when
		when(pizzaRestService.pizzaSizes()).thenReturn(sizes);
		List<String> sizesList = pizzaRestService.pizzaSizes();

		// then
		assertThat(sizesList.get(0), equalTo(PizzaSize.SMALL.getDescription()));
		assertThat(sizesList.get(1), equalTo(PizzaSize.MEDIUM.getDescription()));
		assertThat(sizesList.get(2), equalTo(PizzaSize.LARGE.getDescription()));
		assertThat(sizesList.get(3), equalTo(PizzaSize.XLARGE.getDescription()));
	}

}
