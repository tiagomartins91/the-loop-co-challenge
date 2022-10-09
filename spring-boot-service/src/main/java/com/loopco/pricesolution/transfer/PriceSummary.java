package com.loopco.pricesolution.transfer;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Builder
public class PriceSummary {

    private final LocalDateTime priceDate;
    private final Long brandId;
    private final Integer productId;
    private final String curr;
    private final BigDecimal vat;
    private final BigDecimal vatAmount;
    private final BigDecimal price;
    private final BigDecimal finalPrice;

}
