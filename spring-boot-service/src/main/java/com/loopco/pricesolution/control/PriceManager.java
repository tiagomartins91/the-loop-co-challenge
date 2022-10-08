package com.loopco.pricesolution.control;

import com.loopco.pricesolution.entity.Price;
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

        Price price = getPrice(priceList, priceRequest.getRequestedDateTime());

        //TODO - catch null here and error logging

        final PriceSummary priceSummary = PriceSummary.builder()
                .priceDate(priceRequest.getRequestedDateTime())
                .brandId(price.getBrandId())
                .productId(priceRequest.getProductId())
                .price(price.getPrice())
                .curr(price.getCurrency())
                .build();

        return Optional.of(priceSummary);
    }

    private Price getPrice(List<Price> priceList, LocalDateTime requestedDateTime) {
        return priceList.stream()
                .filter(price -> price.isStartDateEqualOrBefore(requestedDateTime) && price.isEndDateEqualOrAfter(requestedDateTime))
                .max(Comparator.comparing(Price::getPriority))
                .orElse(null);

    }
}
