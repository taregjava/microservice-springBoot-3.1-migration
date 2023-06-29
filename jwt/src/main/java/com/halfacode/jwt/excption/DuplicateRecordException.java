package com.halfacode.jwt.excption;

public class DuplicateRecordException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String code;

    public DuplicateRecordException() {
        super(Error.ERROR_EXIST.getDescription());
        this.code = Error.ERROR_EXIST.getCode();
    }

    public String getCode() {
        return code;
    }
}
