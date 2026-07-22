package com.bcnc.pruebatecnica.domain.repository;

import com.bcnc.pruebatecnica.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Puerto de Salida (Outbound Port) en la Arquitectura Hexagonal.
 * Define el contrato que debe implementar la infraestructura de persistencia.
 */
public interface PriceRepositoryPort {

  /**
   * Obtiene la tarifa de precio aplicable con mayor prioridad para una cadena,
   * un producto y una fecha de aplicación específicas.
   *
   * @param applicationDate Fecha objetivo en la que debe estar vigente la tarifa.
   * @param productId       Identificador del producto.
   * @param brandId         Identificador de la cadena/marca.
   * @return Un {@link Optional} que contiene la tarifa {@link Price} de mayor prioridad aplicable,
   *         o vacío si no existe ninguna tarifa vigente.
   */
  Optional<Price> findApplicablePrice(LocalDateTime applicationDate,
                                      Integer productId, Integer brandId);
}