package com.loopco.pricesolution.control;

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

        final PriceSummary priceSummary = buildPriceSummary(priceRequest.getRequestedDateTime(), price);

        return Optional.of(priceSummary);
    }

    private PriceSummary buildPriceSummary(LocalDateTime requestedPriceDate, Price price) {
        return PriceSummary.builder()
                .priceDate(requestedPriceDate)
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .finalPrice(price.getPrice())
                .vat(price.getVat())
                .curr(price.getCurrency())
                .build();
    }

    private Optional<Price> getPrice(List<Price> priceList, LocalDateTime requestedDateTime) {
        return priceList.stream()
                .filter(price -> price.isStartDateEqualOrBefore(requestedDateTime) && price.isEndDateEqualOrAfter(requestedDateTime))
                .max(Comparator.comparing(Price::getPriority));

    }
}
