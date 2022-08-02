package com.meena.takitaki.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.net.URI;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class TacoDesignControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void designTaco() throws Exception {
    mockMvc.perform(get(URI.create("/design")))
        .andExpect(status().isOk())
        .andExpect(view().name("design-taco"))
        .andExpect(content().string(Matchers.containsString("Design your taco!")));
  }
}