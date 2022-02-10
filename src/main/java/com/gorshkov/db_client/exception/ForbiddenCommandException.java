package com.gorshkov.db_client.exception;

public class ForbiddenCommandException extends Throwable {
    public ForbiddenCommandException(String message) {
        super(message);
    }
}
