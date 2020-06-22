package pl.mateuszprzeczek;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import pl.mateuszprzeczek.domain.Ingredient;
import pl.mateuszprzeczek.domain.Pizza;
import pl.mateuszprzeczek.repository.PizzaRepository;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

  @Bean
  public CommandLineRunner dataLoader(pl.mateuszprzeczek.repository.IngredientRepository repo,
         PizzaRepository pizzaRepo) { // user repo for ease of testing with a built-in user
    return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        Ingredient thinCake = new Ingredient("THIN", "ciasto cienkie");
        thinCake.setType(Ingredient.Type.WRAP);
        Ingredient fatCake = new Ingredient("FATT", "ciasto grube");
        fatCake.setType(Ingredient.Type.WRAP);
        Ingredient salami = new Ingredient("SALA", "salami");
        salami.setType(Ingredient.Type.PROTEIN);
        Ingredient chicken = new Ingredient("CHKN", "kawałki kurczaka");
        chicken.setType(Ingredient.Type.PROTEIN);
        Ingredient tuna = new Ingredient("TUNA", "tuńczyk");
        tuna.setType(Ingredient.Type.PROTEIN);
        Ingredient onion = new Ingredient("ONIN", "cebula");
        onion.setType(Ingredient.Type.VEGGIES);
        Ingredient mashrooms = new Ingredient("MHRM", "pieczarki");
        mashrooms.setType(Ingredient.Type.VEGGIES);
        Ingredient olives = new Ingredient("OLVS", "oliwki");
        olives.setType(Ingredient.Type.VEGGIES);
        Ingredient cheddar = new Ingredient("CHED", "ser cheddar");
        cheddar.setType(Ingredient.Type.CHEESE);
        Ingredient mozarella = new Ingredient("MZRA", "ser mozarella");
        mozarella.setType(Ingredient.Type.CHEESE);
        Ingredient gorgonsola = new Ingredient("GRGZ", "ser gorgonzola");
        gorgonsola.setType(Ingredient.Type.CHEESE);
        Ingredient parmesan = new Ingredient("PARM", "ser parmezan");
        parmesan.setType(Ingredient.Type.CHEESE);
        Ingredient tomato = new Ingredient("TMTO", "sos pomidorowy");
        tomato.setType(Ingredient.Type.SAUCE);
        Ingredient sourCream = new Ingredient("CREM", "sos śmietanowy");
        sourCream.setType(Ingredient.Type.SAUCE);
        repo.save(thinCake);
        repo.save(fatCake);
        repo.save(salami);
        repo.save(chicken);
        repo.save(tuna);
        repo.save(onion);
        repo.save(mashrooms);
        repo.save(olives);
        repo.save(cheddar);
        repo.save(mozarella);
        repo.save(gorgonsola);
        repo.save(parmesan);
        repo.save(tomato);
        repo.save(sourCream);
        
        
        
        Pizza pizza1 = new Pizza();
        pizza1.setName("Mozarella");
        pizza1.setIngredients(Arrays.asList(mozarella, tomato, thinCake));
        pizza1.setPrice(18);
        pizzaRepo.save(pizza1);

        Pizza pizza2 = new Pizza();
        pizza2.setName("Salami");
        pizza2.setIngredients(Arrays.asList(tomato, mozarella, salami, thinCake));
        pizza2.setPrice(20);
        pizzaRepo.save(pizza2);

        Pizza pizza3 = new Pizza();
        pizza3.setName("Vege");
        pizza3.setIngredients(Arrays.asList(tomato, onion, mashrooms, mozarella, thinCake));
        pizza3.setPrice(20);
        pizzaRepo.save(pizza3);
        
        Pizza pizza4 = new Pizza();
        pizza4.setName("Tuna");
        pizza4.setIngredients(Arrays.asList(tomato, onion, tuna, mozarella, olives, thinCake));
        pizza4.setPrice(22);
        pizzaRepo.save(pizza4);
        
        Pizza pizza5 = new Pizza();
        pizza5.setName("4Sery");
        pizza5.setIngredients(Arrays.asList(tomato, mozarella, cheddar, gorgonsola, parmesan, thinCake));
        pizza5.setPrice(24);
        pizzaRepo.save(pizza5);

      }
    };
  }
  
}