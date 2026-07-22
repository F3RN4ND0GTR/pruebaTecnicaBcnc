package com.bcnc.pruebatecnica.application.port.in;

import com.bcnc.pruebatecnica.domain.model.Price;
import java.time.LocalDateTime;

public interface GetApplicablePriceUseCase {
  Price execute(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
