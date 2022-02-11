package com.gorshkov.db_client.exception;

public class IllegalQueryException extends RuntimeException {
    public IllegalQueryException(String message) {
        super(message);
    }
}
