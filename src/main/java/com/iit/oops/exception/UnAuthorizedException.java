package com.iit.oops.exception;

public class UnAuthorizedException extends Throwable {
    private final int code;

    public UnAuthorizedException() {
        this(500);
    }

    public UnAuthorizedException(int code) {
        this(code, "You are not authorized to make this change", null);
    }

    public UnAuthorizedException(int code, String message) {
        this(code, message, null);
    }

    public UnAuthorizedException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
