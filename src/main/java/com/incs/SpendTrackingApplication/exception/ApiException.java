package com.incs.SpendTrackingApplication.exception;

import com.incs.SpendTrackingApplication.response.generics.ResponseCode;

public class ApiException extends Exception {

    private int code;
    private String message;

    public ApiException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
