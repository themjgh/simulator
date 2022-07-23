package com.shaparak.simulator.dto;

import org.springframework.http.HttpStatus;

public  class Response <T extends ResponseBody> {
    protected String message;
    protected RequestStatus statusCode;

    protected T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RequestStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(RequestStatus statusCode) {
        this.statusCode = statusCode;
    }
}
