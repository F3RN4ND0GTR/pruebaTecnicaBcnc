package com.bcnc.pruebatecnica.application.services;

import com.bcnc.pruebatecnica.application.dto.PriceResponse;
import com.bcnc.pruebatecnica.application.port.in.GetApplicablePriceUseCase;
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
  public PriceResponse execute(LocalDateTime applicationDate, Integer productId, Integer brandId) {
    return priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId)
        .map(this::mapToResponse)
        .orElseThrow(() -> new RuntimeException("Price not found for the given criteria"));
  }

  private PriceResponse mapToResponse(Price price) {
    return PriceResponse.builder()
        .productId(price.getProductId())
        .brandId(price.getBrandId())
        .priceList(price.getPriceList())
        .startDate(price.getStartDate())
        .endDate(price.getEndDate())
        .price(price.getPrice())
        .currency(price.getCurrency())
        .build();
  }
}