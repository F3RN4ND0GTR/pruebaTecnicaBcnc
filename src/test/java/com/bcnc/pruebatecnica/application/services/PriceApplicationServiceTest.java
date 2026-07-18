package com.bcnc.pruebatecnica.application.services;

import com.bcnc.pruebatecnica.application.dto.PriceResponse;
import com.bcnc.pruebatecnica.domain.model.Price;
import com.bcnc.pruebatecnica.domain.repository.PriceRepositoryPort;
import com.bcnc.pruebatecnica.infrastructure.adapters.in.web.exception.PriceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test unitario puro para el servicio de aplicación {@link PriceApplicationService}.
 * Se aísla por completo la persistencia utilizando Mockito.
 */
@ExtendWith(MockitoExtension.class)
class PriceApplicationServiceTest {

  @Mock
  private PriceRepositoryPort priceRepositoryPort;

  @InjectMocks
  private PriceApplicationService priceApplicationService;

  @Test
  @DisplayName("Debe retornar PriceResponse correctamente cuando el puerto devuelve un precio")
  void shouldReturnPriceResponseWhenPriceExists() {
    // GIVEN (Preparación del escenario)
    LocalDateTime targetDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
    Integer productId = 35455;
    Integer brandId = 1;

    Price mockPrice = Price.builder()
        .id(1L)
        .brandId(brandId)
        .productId(productId)
        .priceList(2)
        .startDate(targetDate.minusHours(1))
        .endDate(targetDate.plusHours(5))
        .priority(1)
        .price(new BigDecimal("25.45"))
        .currency("EUR")
        .build();

    when(priceRepositoryPort.findApplicablePrice(targetDate, productId, brandId))
        .thenReturn(Optional.of(mockPrice));

    // WHEN
    PriceResponse response = priceApplicationService.execute(targetDate, productId, brandId);

    // THEN
    assertNotNull(response);
    assertEquals(productId, response.getProductId());
    assertEquals(brandId, response.getBrandId());
    assertEquals(2, response.getPriceList());
    assertEquals(new BigDecimal("25.45"), response.getPrice());
    assertEquals("EUR", response.getCurrency());

    verify(priceRepositoryPort, times(1)).findApplicablePrice(targetDate, productId, brandId);
  }

  @Test
  @DisplayName("Debe lanzar PriceNotFoundException cuando el puerto no encuentra ninguna tarifa")
  void shouldThrowPriceNotFoundExceptionWhenPriceDoesNotExist() {
    // GIVEN
    LocalDateTime targetDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
    Integer productId = 99999; // ID inexistente
    Integer brandId = 1;

    when(priceRepositoryPort.findApplicablePrice(targetDate, productId, brandId))
        .thenReturn(Optional.empty());

    // WHEN & THEN
    PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> priceApplicationService.execute(targetDate, productId, brandId));

    assertEquals("No se encontró una tarifa aplicable para el producto e identificadores indicados", exception.getMessage());
    verify(priceRepositoryPort, times(1)).findApplicablePrice(targetDate, productId, brandId);
  }
}