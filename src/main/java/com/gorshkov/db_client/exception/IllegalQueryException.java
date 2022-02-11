package com.gorshkov.db_client.exception;

import java.sql.SQLException;

public class IllegalQueryException extends RuntimeException {
    public IllegalQueryException(String message) {
        super(message);
    }

    public IllegalQueryException(String message, SQLException e) {
        super(message, e);
    }
}
