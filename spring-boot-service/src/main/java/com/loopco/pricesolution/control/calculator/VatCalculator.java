package com.loopco.pricesolution.control.calculator;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VatCalculator implements TaxCalculator {
    private final BigDecimal oneHundredValue = new BigDecimal("100");

    private final BigDecimal finalPrice;
    private final BigDecimal vat;

    public VatCalculator(@NonNull BigDecimal finalPrice, @NonNull BigDecimal vat) {
        this.finalPrice = finalPrice;
        this.vat = vat;
    }

    @Override
    public CalculatorResult calculate() {

        final BigDecimal vatAmout = this.finalPrice.subtract(finalPrice.divide(calcDivisor(), 2, RoundingMode.HALF_UP));
        final BigDecimal priceWithoutVat = this.finalPrice.subtract(vatAmout);

        return CalculatorResult.of(priceWithoutVat, this.finalPrice, this.vat, vatAmout);
    }

    private BigDecimal calcDivisor() {
        return this.vat.divide(oneHundredValue, 2, RoundingMode.HALF_UP)
                .add(BigDecimal.valueOf(1));
    }
}
