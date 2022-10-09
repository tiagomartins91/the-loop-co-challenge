package com.loopco.pricesolution.exception;

import lombok.Getter;

@Getter
public class PriceException extends RuntimeException {

    private final int code;
    private final String message;

    public PriceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
