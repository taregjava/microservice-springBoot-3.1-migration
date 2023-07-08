package com.halfacode.jwt.excption;

import com.halfacode.jwt.utils.UserUtils;

public enum Error {
    ERROR_CREATE(UserUtils.CREATE_ERROR_CODE, UserUtils.CREATE_ERROR),
    ERROR_UPDATE(UserUtils.UPDATE_ERROR_CODE, UserUtils.UPDATE_ERROR),
    ERROR_DELETE(UserUtils.DELETE_ERROR_CODE, UserUtils.DELETE_ERROR),
    ERROR_NOT_EXIST(UserUtils.RECORD_DOES_NOT_EXIST_CODE, UserUtils.RECORD_DOES_NOT_EXIST),
    ERROR_EXIST(UserUtils.RECORD_ALREADY_EXISTS_CODE, UserUtils.RECORD_ALREADY_EXISTS),
    ERROR_NO_RECORD(UserUtils.NO_RECORDS_FOUND_CODE, UserUtils.NO_RECORDS_FOUND),
    ERROR_INVALID_REQ(UserUtils.INVALID_REQUEST_CODE, UserUtils.INVALID_REQUEST),
    ERROR_INVALID_JSON(UserUtils.INVALID_JSON_CODE, UserUtils.INVALID_JSON),
    ERROR_GENERAL_MSG(UserUtils.GENERAL_ERROR_CODE, UserUtils.GENERAL_ERROR);

    private final String code;
    private final String description;

    private Error(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }


}
