package com.loopco.pricesolution.exception;

public class PriceNotFoundException extends PriceException {

    public PriceNotFoundException(int code, String message) {
        super(code, message);
    }

    public static PriceNotFoundException priceNotFound() {
        return new PriceNotFoundException(404, "Price not found!");
    }
}
