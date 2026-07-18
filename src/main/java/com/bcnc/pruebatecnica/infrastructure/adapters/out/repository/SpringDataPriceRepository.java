package com.bcnc.pruebatecnica.infrastructure.adapters.out.repository;

import com.bcnc.pruebatecnica.infrastructure.adapters.out.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
  @Query("SELECT p "
      + "FROM PriceEntity p "
      + "WHERE p.productId = :productId AND p.brandId = :brandId "
      + "AND :applicationDate BETWEEN p.startDate AND p.endDate "
      + "ORDER BY p.priority DESC, p.startDate DESC") // <-- Añadido , p.startDate DESC
  List<PriceEntity> findApplicablePrices(
      @Param("applicationDate") LocalDateTime applicationDate,
      @Param("productId") Integer productId,
      @Param("brandId") Integer brandId
  );
}