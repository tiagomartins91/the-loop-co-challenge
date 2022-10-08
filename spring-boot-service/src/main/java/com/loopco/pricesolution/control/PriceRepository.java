package com.loopco.pricesolution.control;

import com.loopco.pricesolution.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "SELECT p FROM Price p WHERE p.brand.id = :brandId AND p.productId = :productId AND (p.startDate >= :requestedDate or p.endDate >= :requestedDate)")
    List<Price> getAllPricesFromStartDate(@Param("brandId") Long brandId, @Param("productId") Integer productId, @Param("requestedDate") LocalDateTime requestedDate);
}
