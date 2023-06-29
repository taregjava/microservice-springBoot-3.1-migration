package com.halfacode.jwt.utils;

import java.time.Year;

public class UserUtils {

    public static final String ERROR_OCCURRED = "Error occurred while ";
    public static final String PLACE_HOLDER = "%s";
    public static final String CREATE_ERROR = ERROR_OCCURRED + "adding " + PLACE_HOLDER;
    public static final String READ_ERROR = ERROR_OCCURRED + "finding " + PLACE_HOLDER;
    public static final String UPDATE_ERROR = ERROR_OCCURRED + "updating " + PLACE_HOLDER;
    public static final String DELETE_ERROR = ERROR_OCCURRED + "removing " + PLACE_HOLDER;
    public static final String RECORD_DOES_NOT_EXIST = "Record does not exist";
    public static final String RECORD_ALREADY_EXISTS = "Record already exists";
    public static final String NO_RECORDS_FOUND = "No records found";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String INVALID_JSON = "Unable to Parse JSON please provide a valid JSON";
    public static final String REMOVED = "Removed " + PLACE_HOLDER;
    public static final String GENERAL_ERROR = "Something went wrong. Please try again.";

    public static final String CREATE_ERROR_CODE = "ERR_AR_MS_01";
    public static final String UPDATE_ERROR_CODE = "ERR_AR_MS_02";
    public static final String DELETE_ERROR_CODE = "ERR_AR_MS_03";
    public static final String RECORD_DOES_NOT_EXIST_CODE = "ERR_AR_MS_04";
    public static final String RECORD_ALREADY_EXISTS_CODE = "ERR_AR_MS_05";
    public static final String NO_RECORDS_FOUND_CODE = "ERR_AR_MS_06";
    public static final String INVALID_REQUEST_CODE = "ERR_AR_MS_07";
    public static final String GENERAL_ERROR_CODE = "ERR_AR_MS_08";
    public static final String INVALID_JSON_CODE = "ERR_AR_MS_09";
    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_CODE_DESC = "OK";

    public static final String MSG_FIELD = "Message";
    public static final String ACCOUNT_EXIST_CODE = "001";
    public static final String ACCOUNT_EXIST_MESSAGE = "This User already has an account";

    public static final String ACCOUNT_CREATION_SUCCESS= "002";
    public static final String ACCOUNT_CREATION_MESSAGE= "Account has been successfully created";

    public static String generateAccountNumber(){

        Year currentYear = Year.now();
        int min = 100000;
        int max = 999999;
        int randNumber= (int) Math.floor(Math.random() * (max - min + 1) +min);

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        StringBuilder accountNumber= new StringBuilder();
        return accountNumber.append(year).append(randomNumber).toString();
    }
}
