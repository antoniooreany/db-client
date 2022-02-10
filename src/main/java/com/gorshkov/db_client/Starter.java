package com.gorshkov.db_client;

import com.gorshkov.db_client.exception.ForbiddenCommandException;
import com.gorshkov.db_client.service.DatabaseClientManager;

import java.sql.SQLException;

public class Starter {
    public static void main(String[] args) throws SQLException, ForbiddenCommandException {
        DatabaseClientManager manager = new DatabaseClientManager();
        manager.start();
    }
}
