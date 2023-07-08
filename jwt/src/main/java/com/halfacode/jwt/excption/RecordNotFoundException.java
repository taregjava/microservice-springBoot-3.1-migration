package com.halfacode.jwt.excption;

public class RecordNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private final String code;

    public RecordNotFoundException() {
        super(Error.ERROR_NOT_EXIST.getDescription());
        this.code = Error.ERROR_NOT_EXIST.getCode();
    }

    public RecordNotFoundException(String message) {
        super(message);
        this.code = Error.ERROR_NOT_EXIST.getCode();
    }

    public String getCode() {
        return code;
    }
}