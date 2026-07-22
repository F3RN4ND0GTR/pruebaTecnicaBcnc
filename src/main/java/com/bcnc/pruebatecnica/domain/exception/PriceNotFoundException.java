package com.bcnc.pruebatecnica.domain.exception;

/**
 * Excepción lanzada cuando no se encuentra ninguna tarifa de precios aplicable
 * para los criterios de búsqueda provistos.
 */
public class PriceNotFoundException extends RuntimeException {
  public PriceNotFoundException(String message) {
    super(message);
  }
}