package com.loopco.pricesolution.control;

import com.loopco.pricesolution.control.calculator.CalculatorResult;
import com.loopco.pricesolution.control.calculator.VatCalculator;
import com.loopco.pricesolution.entity.Price;
import com.loopco.pricesolution.exception.PriceNotFoundException;
import com.loopco.pricesolution.transfer.PriceRequest;
import com.loopco.pricesolution.transfer.PriceSummary;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class PriceManager implements PriceControl {

    private final PriceRepository priceRepository;

    @Override
    public Optional<PriceSummary> getPrice(@NonNull PriceRequest priceRequest) {

        final LocalDateTime startOfTheDay = priceRequest.getRequestedDateTime()
                .toLocalDate()
                .atTime(LocalTime.MIDNIGHT);

        List<Price> priceList = priceRepository.getAllPricesFromStartDate(priceRequest.getBrandId(), priceRequest.getProductId(), startOfTheDay);

        Price price = getPrice(priceList, priceRequest.getRequestedDateTime())
                .orElseThrow(PriceNotFoundException::priceNotFound);

        final CalculatorResult priceCalculatorResult = calcVat(price);


        final PriceSummary priceSummary = PriceSummary.builder()
                .priceDate(priceRequest.getRequestedDateTime())
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .curr(price.getCurrency())
                .vat(priceCalculatorResult.getVat())
                .vatAmout(priceCalculatorResult.getVatAmount())
                .price(priceCalculatorResult.getPrice())
                .finalPrice(priceCalculatorResult.getFinalPrice())
                .build();

        return Optional.of(priceSummary);
    }

    private CalculatorResult calcVat(Price price) {
        VatCalculator vatCalculator = new VatCalculator(price.getPrice(), price.getVat());
        return vatCalculator.calculate();
    }

    private Optional<Price> getPrice(List<Price> priceList, LocalDateTime requestedDateTime) {
        return priceList.stream()
                .filter(price -> price.isStartDateEqualOrBefore(requestedDateTime) && price.isEndDateEqualOrAfter(requestedDateTime))
                .max(Comparator.comparing(Price::getPriority));

    }
}
