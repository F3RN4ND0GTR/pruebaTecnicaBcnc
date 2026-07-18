package com.bcnc.pruebatecnica.infrastructure.adapters.in.web.exception;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

/**
 * Estructura de datos estandarizada para responder errores HTTP de la API.
 */
@Value
@Builder
public class ErrorResponse {
  LocalDateTime timestamp;
  int status;
  String error;
  String message;
  String path;
}