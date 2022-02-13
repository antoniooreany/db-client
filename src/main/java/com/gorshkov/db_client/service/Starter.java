package com.gorshkov.db_client.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Starter {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "user";
    private static final String PASSWORD = "7777777";

//    SELECT * FROM  db.persons;
//    INSERT INTO db.persons VALUES (77, 'value0', 'value1', 'value2', 'value3');
//    UPDATE db.persons SET PersonID = 55 WHERE PersonID = 3;
//    DELETE FROM db.persons WHERE PersonID = 55;
    public static void main(String[] args) throws SQLException, IOException {
        Statement statement = getStatement();
        Scanner scanner = new Scanner(System.in);
        extracted(statement, scanner);
    }

    private static void extracted(Statement statement, Scanner scanner) throws SQLException, IOException {
        while (true) {
            System.out.println("Please enter your query");
            String query;
            if (!(query = scanner.nextLine()).isEmpty()) {
                QueryHandler handler = new QueryHandler(statement, query);
                handler.handle();
            }
        }
    }

    private static Statement getStatement() {
        Statement statement;
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to the DB cannot be established with the current credentials", e);
        }
        return statement;
    }
}
