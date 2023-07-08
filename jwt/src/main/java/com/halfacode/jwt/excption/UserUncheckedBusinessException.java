package com.halfacode.jwt.excption;

public class UserUncheckedBusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private final String code;

    public UserUncheckedBusinessException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public UserUncheckedBusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
