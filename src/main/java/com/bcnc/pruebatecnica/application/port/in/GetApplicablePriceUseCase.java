package com.bcnc.pruebatecnica.application.port.in;

import com.bcnc.pruebatecnica.application.dto.PriceResponse;
import java.time.LocalDateTime;

public interface GetApplicablePriceUseCase {
  PriceResponse execute(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
