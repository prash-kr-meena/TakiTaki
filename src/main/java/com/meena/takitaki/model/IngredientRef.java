package com.meena.takitaki.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class IngredientRef {
  private final String ingredient;
  private final String taco;
  private final String tacoKey;
}
