package com.meena.takitaki.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class TacoOrder {
  @Id
  private Long id;

  private LocalDateTime placedAt;

  @NotBlank(message = "Delivery Name is required!")
  @Size(max = 50, message = "maximum length of 50")
  private String deliveryName;

  @NotBlank(message = "Delivery Street is required!")
  @Size(max = 50, message = "maximum length of 50")
  private String deliveryStreet;

  @NotBlank(message = "Delivery City is required!")
  @Size(max = 50, message = "maximum length of 50")
  private String deliveryCity;

  @NotBlank(message = "Delivery State is required!")
  @Size(min = 2, max = 2, message = "State can only have a size of 2")
  private String deliveryState;

  @NotBlank(message = "Delivery Zip is required!")
  @Size(max = 10, message = "maximum length of 10")
  private String deliveryZip;

  //  @CreditCardNumber(message = "Not a valid Credit Card Number")
  @Size(min = 16, max = 16, message = "should be of length 16")
  private String ccNumber;

  @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([2-9][0-9])$", message = "Expiration does not match the format of MM/YY")
  @Size(min = 5, max = 5, message = "should be of length 5")
  private String ccExpiration;

  @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
  private String ccCVV;

  @Size(min = 1, message = "An order need at least one taco")
  private List<Taco> tacos = new ArrayList<>(); // Zero Tacos

  public void addTaco(Taco taco) {
    taco.setCreatedAt(LocalDateTime.now());
    this.tacos.add(taco);
  }
}
