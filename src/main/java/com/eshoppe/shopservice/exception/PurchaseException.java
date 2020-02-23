package com.eshoppe.shopservice.exception;

import org.springframework.http.HttpStatus;

public class PurchaseException extends Exception{

    private HttpStatus status;

    private String message;

    public PurchaseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public PurchaseException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
