package com.iit.oops.exception;

public class BuyNothingException extends Exception {
    private final String code;
    private final String title = "Your request did not pass validation";
    private String detail;
    private String instance;


    public BuyNothingException() {
        this("500");
    }

    public BuyNothingException(String code) {
        this(code, "Error while processing the request", null);
    }

    public BuyNothingException(String code, String message) {
        this(code, message, null);
    }

    public BuyNothingException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "{" +
                "code:" + '\"' + code + '\"' +
                ", title:" + '\"' + title + '\"' +
                ", detail:" + '\"' + detail + '\"' +
                ", instance:" + '\"' + instance + '\"' +
                '}';
    }

    public String getCode() {
        return code;
    }
}
