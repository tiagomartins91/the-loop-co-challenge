package com.loopco.pricesolution.control.calculator;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VatCalculatorTest {

    @Test
    void should_get_vat_amout_and_price_given_final_price_and_vat() {

        VatCalculator calc1 = new VatCalculator(new BigDecimal("35.50"), new BigDecimal("23"));
        CalculatorResult result1 = calc1.calculate();

        assertEquals(new BigDecimal("23"), result1.getVat());
        assertEquals(new BigDecimal("6.64"), result1.getVatAmount());
        assertEquals(new BigDecimal("28.86"), result1.getPrice());
        assertEquals(new BigDecimal("35.50"), result1.getFinalPrice());

        VatCalculator calc2 = new VatCalculator(new BigDecimal("20"), new BigDecimal("7"));
        CalculatorResult result2 = calc2.calculate();

        assertEquals(new BigDecimal("7"), result2.getVat());
        assertEquals(new BigDecimal("1.31"), result2.getVatAmount());
        assertEquals(new BigDecimal("18.69"), result2.getPrice());
        assertEquals(new BigDecimal("20"), result2.getFinalPrice());
    }

}
