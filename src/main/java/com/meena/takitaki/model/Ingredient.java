package com.meena.takitaki.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class Ingredient implements Persistable<String> {
  @Id
  private String id;
  private String name;
  private Type type;

  @Override
  public boolean isNew() {
    return id == null; // probably not the right implementation
  }

  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }
}
