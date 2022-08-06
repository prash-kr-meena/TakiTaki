package com.meena.takitaki.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
public class TacoOrder {
  private Long id;
  private LocalDateTime placedAt;
  @NotBlank(message = "Delivery Name is required!")
  private String deliveryName;
  @NotBlank(message = "Delivery Street is required!")
  private String deliveryStreet;
  @NotBlank(message = "Delivery City is required!")
  private String deliveryCity;
  @NotBlank(message = "Delivery State is required!")
  private String deliveryState;
  @NotBlank(message = "Delivery Zip is required!")
  private String deliveryZip;
  //  @CreditCardNumber(message = "Not a valid Credit Card Number")
  private String ccNumber;
  @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Expiration does not match the format of MM/YY")
  private String ccExpiration;
  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  private String ccCVV;
  @Size(min = 1, message = "An order need at least one taco")
  private List<Taco> tacos = new ArrayList<>(); // Zero Tacos

  public void addTaco(Taco taco) {
    this.tacos.add(taco);
  }
}
