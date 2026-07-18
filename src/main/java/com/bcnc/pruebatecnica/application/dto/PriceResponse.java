package com.bcnc.pruebatecnica.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;
/**
 * Objeto de transferencia de datos (DTO) que representa la respuesta detallada
 * de una tarifa de precio aplicable devuelta al cliente.
 *
 * <p>Se utiliza la anotación {@link Value} de Lombok para garantizar la inmutabilidad
 * del objeto durante todo el ciclo de transporte de datos.
 * </p>
 */

@Value
@Builder
public class PriceResponse {

  /** Identificador del producto. */
  Integer productId;

  /** Identificador de la cadena o marca (ej. 1 para ZARA). */
  Integer brandId;

  /** Identificador de la tarifa de precios aplicable. */
  Integer priceList;

  /** Fecha de inicio en la que aplica la tarifa. */
  LocalDateTime startDate;

  /** Fecha de fin en la que aplica la tarifa. */
  LocalDateTime endDate;

  /** Valor monetario final de la tarifa. */
  BigDecimal price;

  /** Código de la divisa (ej. "EUR"). */
  String currency;
}