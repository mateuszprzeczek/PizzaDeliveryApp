package pl.mateuszprzeczek.web.api.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pl.mateuszprzeczek.domain.Ingredient;
import pl.mateuszprzeczek.repository.IngredientRepository;

@ExtendWith(MockitoExtension.class)
class IngredientRestServiceTest {

	@InjectMocks
	IngredientRestService ingredientRestService;
	@Mock
	IngredientRepository ingredientRepository;

	@Test
	void testGetAllIngredientsShouldReturnNotNullValue() {

		// given
		Iterable<Ingredient> ingredients = prepareIngredientsData();
		given(ingredientRepository.findAll()).willReturn(ingredients);

		// when
		Iterable<Ingredient> returnedIngredients = ingredientRestService.getIngredients();

		// then
		assertThat(returnedIngredients.iterator().next().getName(), notNullValue());
	}

	private Iterable<Ingredient> prepareIngredientsData() {

		Ingredient thinCake = new Ingredient("THIN", "ciasto cienkie");
		thinCake.setType(Ingredient.Type.WRAP);

		Ingredient salami = new Ingredient("SALA", "salami");
		salami.setType(Ingredient.Type.PROTEIN);

		Ingredient mozarella = new Ingredient("MZRA", "mozarella");
		salami.setType(Ingredient.Type.PROTEIN);

		return Arrays.asList(thinCake, salami, mozarella);
	}

	@Test
	void testGetIngredientByIdShouldNotBeEmpty() {

		// given
		Ingredient thinCake = new Ingredient("THIN", "ciasto cienkie");
		thinCake.setType(Ingredient.Type.WRAP);

		doReturn(Optional.of(thinCake)).when(ingredientRepository).findById("THIN");

		// when
		Ingredient returnedIngredient = ingredientRestService.ingredientById("THIN");

		// then
		assertThat(returnedIngredient.getId(), equalTo("THIN"));

	}

	@Test
	void testGetPizzaByIdShouldReturnNullValue() {

		// given
		// when
		Ingredient returnedIngredient = ingredientRestService.ingredientById("THIN");

		// then
		assertThat(returnedIngredient, nullValue());
	}

}
