package com.bcnc.pruebatecnica.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

/**
 * Entidad fundamental del Dominio (DDD). Representa la regla de negocio de una tarifa de precios.
 * Esta clase es pura y agnóstica de frameworks o bases de datos.
 *
 * @author Fernando Matilla Méndez
 * @version 1.0.0
 */
@Builder
@Value
public class Price {
  Long id;
  Integer brandId;
  LocalDateTime startDate;
  LocalDateTime endDate;
  Integer priceList;
  Integer productId;
  Integer priority;
  BigDecimal price;
  String currency;
}