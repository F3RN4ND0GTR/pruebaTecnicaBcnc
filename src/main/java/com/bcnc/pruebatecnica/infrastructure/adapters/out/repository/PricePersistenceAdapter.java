package com.bcnc.pruebatecnica.infrastructure.adapters.out.repository;

import com.bcnc.pruebatecnica.domain.model.Price;
import com.bcnc.pruebatecnica.domain.repository.PriceRepositoryPort;
import com.bcnc.pruebatecnica.infrastructure.adapters.out.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Adaptador de infraestructura que implementa el puerto de salida de persistencia.
 * Descoplado completamente de la lógica de dominio.
 */
@Component
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceRepositoryPort {

  private final SpringDataPriceRepository springDataPriceRepository;

  @Override
  public Optional<Price> findApplicablePrice(LocalDateTime applicationDate,
                                             Integer productId, Integer brandId) {
    return springDataPriceRepository.findApplicablePrices(applicationDate, productId, brandId)
        .stream()
        .findFirst()
        .map(this::mapToDomain);
  }

  private Price mapToDomain(PriceEntity entity) {
    return Price.builder()
        .id(entity.getId())
        .brandId(entity.getBrandId())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
        .priceList(entity.getPriceList())
        .productId(entity.getProductId())
        .priority(entity.getPriority())
        .price(entity.getPrice())
        .currency(entity.getCurrency())
        .build();
  }
}