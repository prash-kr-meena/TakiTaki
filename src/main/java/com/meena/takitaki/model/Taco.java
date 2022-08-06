package com.meena.takitaki.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Taco {
  private Long id;
  @NotBlank
  @Size(min = 5, message = "Name must be at least 5 characters long")
  private String name;
  @NotNull
  @Size(min = 1, message = "You must choose atleast one ingredient")
  private List<Ingredient> ingredients;
  private LocalDateTime createdAt;
}
