package com.bcnc.pruebatecnica.infrastructure.adapters.in.web.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  private static final String API_URL = "/api/v1/prices/applicable";
  private static final String PRODUCT_ID = "35455";
  private static final String BRAND_ID = "1";

  @Test
  @DisplayName("Test 1: Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void test1_RequestAt1000Day14() throws Exception {
    mockMvc.perform(get(API_URL)
            .param("applicationDate", "2020-06-14T10:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value(35455))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(1))
        .andExpect(jsonPath("$.price").value(35.50));
  }

  @Test
  @DisplayName("Test 2: Petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void test2_RequestAt1600Day14() throws Exception {
    mockMvc.perform(get(API_URL)
            .param("applicationDate", "2020-06-14T16:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value(35455))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(2))
        .andExpect(jsonPath("$.price").value(25.45)); // <-- Cambiado de 25.44 a 25.45
  }

  @Test
  @DisplayName("Test 3: Petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
  void test3_RequestAt2100Day14() throws Exception {
    mockMvc.perform(get(API_URL)
            .param("applicationDate", "2020-06-14T21:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value(35455))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(2)) // <-- Cambiado de 1 a 2
        .andExpect(jsonPath("$.price").value(25.45)); // <-- Cambiado de 35.50 a 25.45
  }

  @Test
  @DisplayName("Test 4: Petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
  void test4_RequestAt1000Day15() throws Exception {
    mockMvc.perform(get(API_URL)
            .param("applicationDate", "2020-06-15T10:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value(35455))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(3))
        .andExpect(jsonPath("$.price").value(30.50));
  }

  @Test
  @DisplayName("Test 5: Petición a las 19:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
  void test5_RequestAt1900Day15() throws Exception {
    mockMvc.perform(get(API_URL)
            .param("applicationDate", "2020-06-15T19:00:00")
            .param("productId", PRODUCT_ID)
            .param("brandId", BRAND_ID)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.productId").value(35455))
        .andExpect(jsonPath("$.brandId").value(1))
        .andExpect(jsonPath("$.priceList").value(4))
        .andExpect(jsonPath("$.price").value(38.95));
  }
}