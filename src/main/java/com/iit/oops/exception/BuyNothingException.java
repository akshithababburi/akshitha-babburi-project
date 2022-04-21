package com.iit.oops.exception;

public class BuyNothingException extends Throwable {
    private final int code;

    public BuyNothingException() {
        this(500);
    }

    public BuyNothingException(int code) {
        this(code, "Error while processing the request", null);
    }

    public BuyNothingException(int code, String message) {
        this(code, message, null);
    }

    public BuyNothingException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
