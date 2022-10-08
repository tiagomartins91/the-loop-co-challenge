package com.loopco.pricesolution.control;

import com.loopco.pricesolution.transfer.PriceRequest;
import com.loopco.pricesolution.transfer.PriceSummary;

import java.util.Optional;

public interface PriceControl {

    Optional<PriceSummary> getPrice(PriceRequest priceRequest);
}
