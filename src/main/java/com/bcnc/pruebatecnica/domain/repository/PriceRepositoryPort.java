package com.bcnc.pruebatecnica.domain.repository;

import com.bcnc.pruebatecnica.domain.model.Price;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Puerto de Salida (Outbound Port) en la Arquitectura Hexagonal.
 * Interfaz que define las operaciones de inversión de dependencia para la persistencia.
 *
 * <p>El dominio la expone para que la infraestructura implemente la recuperación real
 * de los datos sin acoplar el núcleo de negocio a una base de datos específica.
 * </p>
 *
 * @author Fernando Matilla Méndez
 * @version 1.0.0
 */
public interface PriceRepositoryPort {
  /**
   * Busca todas las tarifas aplicables en el sistema que coincidan con el producto,
   * la cadena y cuyo rango de fechas comprenda la fecha de aplicación solicitada.
   *
   * @param applicationDate Fecha objetivo en la que debe estar vigente la tarifa.
   * @param productId       Identificador único del producto.
   * @param brandId         Identificador de la cadena o marca (ej. 1 para ZARA).
   * @return Lista de objetos de dominio {@link Price} que cumplen las condiciones temporales.
   */
  Optional<Price> findApplicablePrice(LocalDateTime applicationDate,
                                      Integer productId, Integer brandId);
}