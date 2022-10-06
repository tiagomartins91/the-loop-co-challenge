package com.loopco.pricesolution.control;

import com.loopco.pricesolution.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
