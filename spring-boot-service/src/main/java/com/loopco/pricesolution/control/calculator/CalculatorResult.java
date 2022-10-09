package com.loopco.pricesolution.control.calculator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculatorResult {

    private final BigDecimal price;
    private final BigDecimal finalPrice;
    private final BigDecimal vat;
    private final BigDecimal vatAmount;

    public static CalculatorResult of(BigDecimal price, BigDecimal finalPrice, BigDecimal vat, BigDecimal vatAmount) {
        return new CalculatorResult(price, finalPrice, vat, vatAmount);
    }
}
