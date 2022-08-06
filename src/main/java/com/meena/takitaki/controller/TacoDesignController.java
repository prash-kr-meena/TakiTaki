package com.meena.takitaki.controller;

import com.meena.takitaki.model.Ingredient;
import com.meena.takitaki.model.Ingredient.Type;
import com.meena.takitaki.model.Taco;
import com.meena.takitaki.model.TacoOrder;
import com.meena.takitaki.repository.JdbcIngredientRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@SessionAttributes("tacoOrder")
public class TacoDesignController {
  private final JdbcIngredientRepository ingredientRepository;

  public TacoDesignController(JdbcIngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    //    List<Ingredient> ingredients = Arrays.asList(
    //        new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
    //        new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
    //        new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
    //        new Ingredient("CARN", "Carnitas", Type.PROTEIN),
    //        new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
    //        new Ingredient("LETC", "Lettuce", Type.VEGGIES),
    //        new Ingredient("CHED", "Cheddar", Type.CHEESE),
    //        new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
    //        new Ingredient("SLSA", "Salsa", Type.SAUCE),
    //        new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
    //    );
    List<Ingredient> ingredients = ingredientRepository.findAll();

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }
  }

  @ModelAttribute("tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @ModelAttribute("taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping("/design")
  public String designTaco(Model model) {
    log.info("Model before the Design-Tack page : {}", model);
    return "design-taco";
  }


  @PostMapping("/design")
  public String processTaco(@Valid Taco taco, Errors errors, TacoOrder tacoOrder) {
    log.error("Errors Found : {}", errors);
    if (errors.hasErrors()) {
      return "design-taco";
    }

    tacoOrder.addTaco(taco);
    log.info("Processing taco: {}", taco);
    return "redirect:/orders/current";
  }


  private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients
        .stream()
        .filter(ingredient -> ingredient.getType().equals(type))
        .collect(Collectors.toList());
  }
}
