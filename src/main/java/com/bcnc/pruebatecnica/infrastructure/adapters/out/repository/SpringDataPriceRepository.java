package com.bcnc.pruebatecnica.infrastructure.adapters.out.repository;

import com.bcnc.pruebatecnica.infrastructure.adapters.out.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio nativo de Spring Data JPA
 * para realizar operaciones sobre la entidad {@link PriceEntity}.
 */
@Repository
public interface SpringDataPriceRepository extends JpaRepository<PriceEntity, Long> {

  /**
   * Busca las tarifas aplicables filtrando por fecha, producto y marca.
   * Devuelve los resultados ordenados de mayor a menor prioridad.
   */
  Optional<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
      Integer brandId,
      Integer productId,
      LocalDateTime applicationDateStart,
      LocalDateTime applicationDateEnd
  );
}