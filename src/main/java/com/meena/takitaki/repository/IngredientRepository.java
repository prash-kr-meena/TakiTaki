package com.meena.takitaki.repository;

import com.meena.takitaki.model.Ingredient;
import java.util.Optional;

public interface IngredientRepository {
  Iterable<Ingredient> findAll();

  Optional<Ingredient> findById(String id);

  Ingredient save(Ingredient ingredient);
}
