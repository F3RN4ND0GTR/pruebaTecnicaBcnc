package com.bcnc.pruebatecnica.infrastructure.adapters.in.web.mapper;

import com.bcnc.pruebatecnica.application.dto.PriceResponse;
import com.bcnc.pruebatecnica.domain.model.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceWebMapper {

  public PriceResponse toResponse(Price price) {
    if (price == null) {
      return null;
    }

    return PriceResponse.builder()
        .brandId(price.getBrandId())
        .priceList(price.getPriceList())
        .price(price.getPrice())
        .startDate(price.getStartDate())
        .endDate(price.getEndDate())
        .currency(price.getCurrency())
        .productId(price.getProductId())
        .build();
  }
}