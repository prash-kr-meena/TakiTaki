package com.meena.takitaki.repository;

import com.meena.takitaki.model.Ingredient;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
  List<Ingredient> findAll();

  //  Optional<Ingredient> findById(String id);

  Ingredient save(Ingredient ingredient);
}
