package com.bcnc.pruebatecnica.infrastructure.adapters.in.web.controller;

import com.bcnc.pruebatecnica.application.dto.PriceResponse;
import com.bcnc.pruebatecnica.application.port.in.GetApplicablePriceUseCase;
import com.bcnc.pruebatecnica.infrastructure.adapters.in.web.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST que expone los servicios web de consulta de tarifas de precios.
 */
@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
@Tag(name = "Precios", description = "Endpoints para la gestión y consulta de tarifas de precios")
public class PriceController {

  private final GetApplicablePriceUseCase getApplicablePriceUseCase;

  @GetMapping("/applicable")
  @Operation(
      summary = "Consultar tarifa aplicable",
      description = "Busca la tarifa con mayor prioridad para un producto "
          + "y marca específicos en una fecha determinada.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Operación exitosa",
              content = @Content(schema = @Schema(implementation = PriceResponse.class))),
          @ApiResponse(responseCode = "404",
              description = "Tarifa no encontrada para los criterios especificados",
              content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
      }
  )
  public ResponseEntity<PriceResponse> getApplicablePrice(
      @Parameter(description = "Fecha de aplicación (Formato: yyyy-MM-dd'T'HH:mm:ss)",
          example = "2020-06-14T16:00:00")
      @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
      LocalDateTime applicationDate,

      @Parameter(description = "Identificador único del producto", example = "35455")
      @RequestParam("productId") Integer productId,

      @Parameter(description = "Identificador único de la marca / cadena", example = "1")
      @RequestParam("brandId") Integer brandId) {

    PriceResponse response = getApplicablePriceUseCase.execute(applicationDate, productId, brandId);
    return ResponseEntity.ok(response);
  }
}