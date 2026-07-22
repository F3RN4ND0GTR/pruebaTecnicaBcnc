package com.bcnc.pruebatecnica.application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bcnc.pruebatecnica.domain.exception.PriceNotFoundException;
import com.bcnc.pruebatecnica.domain.model.Price;
import com.bcnc.pruebatecnica.domain.repository.PriceRepositoryPort;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PriceApplicationServiceTest {

  @Mock
  private PriceRepositoryPort priceRepositoryPort;

  @InjectMocks
  private PriceApplicationService priceApplicationService;

  @Test
  @DisplayName("Debe retornar Price correctamente cuando el puerto devuelve un precio")
  void shouldReturnPriceWhenPriceExists() {
    // GIVEN
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
    Price price = priceApplicationService.execute(targetDate, productId, brandId);

    // THEN
    assertNotNull(price);
    assertEquals(productId, price.getProductId());
    assertEquals(brandId, price.getBrandId());
    assertEquals(2, price.getPriceList());
    assertEquals(new BigDecimal("25.45"), price.getPrice());
    assertEquals("EUR", price.getCurrency());

    verify(priceRepositoryPort, times(1)).findApplicablePrice(targetDate, productId, brandId);
  }

  @Test
  @DisplayName("Debe lanzar PriceNotFoundException cuando el puerto no encuentra ninguna tarifa")
  void shouldThrowPriceNotFoundExceptionWhenPriceDoesNotExist() {
    // GIVEN
    LocalDateTime targetDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
    Integer productId = 99999;
    Integer brandId = 1;

    when(priceRepositoryPort.findApplicablePrice(targetDate, productId, brandId))
        .thenReturn(Optional.empty());

    // WHEN & THEN
    PriceNotFoundException exception = assertThrows(
        PriceNotFoundException.class,
        () -> priceApplicationService.execute(targetDate, productId, brandId)
    );

    assertNotNull(exception.getMessage());
    assertTrue(exception.getMessage().contains("tarifa aplicable"));
    verify(priceRepositoryPort, times(1)).findApplicablePrice(targetDate, productId, brandId);
  }
}