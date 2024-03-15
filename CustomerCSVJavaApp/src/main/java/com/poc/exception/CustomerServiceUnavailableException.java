package com.poc.exception;

import lombok.Getter;

@Getter
public class CustomerServiceUnavailableException extends RuntimeException {

    public CustomerServiceUnavailableException(String message) {
        super(message);
    }
}
