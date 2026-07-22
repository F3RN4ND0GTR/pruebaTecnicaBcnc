package com.bcnc.pruebatecnica.application.services;

import com.bcnc.pruebatecnica.application.port.in.GetApplicablePriceUseCase;
import com.bcnc.pruebatecnica.domain.exception.PriceNotFoundException;
import com.bcnc.pruebatecnica.domain.model.Price;
import com.bcnc.pruebatecnica.domain.repository.PriceRepositoryPort;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceApplicationService implements GetApplicablePriceUseCase {

  private final PriceRepositoryPort priceRepositoryPort;

  @Override
  public Price execute(LocalDateTime applicationDate, Integer productId, Integer brandId) {
    return priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId)
        .orElseThrow(() -> new PriceNotFoundException(
            "No se encontró una tarifa aplicable para el producto e identificadores indicados"));
  }
}