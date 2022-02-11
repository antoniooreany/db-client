package com.gorshkov.db_client;

import com.gorshkov.db_client.service.QueryHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Starter {
    private static final String DB_URL = "jdbc:mysql://localhost:3306";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "7777777";

    // SELECT * FROM db.persons
    public static void main(String[] args) throws SQLException, IOException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter your query: ");
                String query;
                while (!(query = scanner.nextLine()).isEmpty()) {
                    QueryHandler queryHandler = new QueryHandler(statement, query);
                    queryHandler.handle();
                }
            }
        }
    }
}
