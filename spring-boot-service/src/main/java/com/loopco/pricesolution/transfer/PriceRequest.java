package com.loopco.pricesolution.transfer;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PriceRequest {

    private final Long brandId;
    private final Integer productId;
    private final LocalDateTime requestedDateTime;

    public static PriceRequestBuilder builder() {
        return new CustomPriceRequest();
    }

    private static class CustomPriceRequest extends PriceRequestBuilder {
        @Override
        public PriceRequest build() {
            assert super.brandId != null : "CustomPriceRequest 'brandId' cannot be null";
            assert super.productId != null : "CustomPriceRequest 'productId' cannot be null";
            assert super.requestedDateTime != null : "CustomPriceRequest 'dateTime' cannot be null";

            return super.build();
        }
    }

}
