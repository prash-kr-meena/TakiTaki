package com.meena.takitaki.model.convertor;


import com.meena.takitaki.model.Ingredient;
import com.meena.takitaki.repository.JdbcIngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
  //  private Map<String, Ingredient> ingredientMap = new HashMap<>();
  private final JdbcIngredientRepository ingredientRepository;


  public IngredientByIdConverter(JdbcIngredientRepository ingredientRepository) {
    //    ingredientMap.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
    //    ingredientMap.put("COTO", new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
    //    ingredientMap.put("GRBF", new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
    //    ingredientMap.put("CARN", new Ingredient("CARN", "Carnitas", Type.PROTEIN));
    //    ingredientMap.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
    //    ingredientMap.put("LETC", new Ingredient("LETC", "Lettuce", Type.VEGGIES));
    //    ingredientMap.put("CHED", new Ingredient("CHED", "Cheddar", Type.CHEESE));
    //    ingredientMap.put("JACK", new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
    //    ingredientMap.put("SLSA", new Ingredient("SLSA", "Salsa", Type.SAUCE));
    //    ingredientMap.put("SRCR", new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
    this.ingredientRepository = ingredientRepository;
  }

  @Override
  public Ingredient convert(String id) {
    //    return ingredientMap.get(id);
    return ingredientRepository.findById(id).orElse(null);
  }
}