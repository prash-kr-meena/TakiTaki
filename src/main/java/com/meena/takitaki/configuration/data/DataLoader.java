package com.meena.takitaki.configuration.data;

import com.meena.takitaki.model.Ingredient;
import com.meena.takitaki.model.Ingredient.Type;
import com.meena.takitaki.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DataLoader implements ApplicationRunner {
  private final IngredientRepository ingredientRepository;

  public DataLoader(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @Override
  public void run(ApplicationArguments args) {
    log.info("Data Insertion STARTED via ApplicationRunner");
    ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Type.valueOf("WRAP")));
    ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Type.valueOf("WRAP")));
    ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Type.valueOf("PROTEIN")));
    ingredientRepository.save(new Ingredient("CARN", "Carnitas", Type.valueOf("PROTEIN")));
    ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Type.valueOf("VEGGIES")));
    ingredientRepository.save(new Ingredient("LETC", "Lettuce", Type.valueOf("VEGGIES")));
    ingredientRepository.save(new Ingredient("CHED", "Cheddar", Type.valueOf("CHEESE")));
    ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Type.valueOf("CHEESE")));
    ingredientRepository.save(new Ingredient("SLSA", "Salsa", Type.valueOf("SAUCE")));
    ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Type.valueOf("SAUCE")));
    log.info("Data Insertion COMPLETED via ApplicationRunner");
  }

}
