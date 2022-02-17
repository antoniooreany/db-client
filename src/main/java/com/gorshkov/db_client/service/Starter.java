package com.gorshkov.db_client.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Scanner;

//    SELECT * FROM  db.persons;
//    INSERT INTO db.persons VALUES (77, 'value0', 'value1', 'value2', 'value3');
//    UPDATE db.persons SET PersonID = 55 WHERE PersonID = 77;
//    DELETE FROM db.persons WHERE PersonID = 55;
public class Starter {

    private static final HashMap<String, String> properties = PropertyReader.getProperties();

    public static void main(String[] args) throws SQLException, IOException {
        Statement statement = getStatement();
        Scanner scanner = new Scanner(System.in);
        extracted(statement, scanner);
    }

    private static void extracted(Statement statement, Scanner scanner) // todo: how to name the method ?
            throws SQLException, IOException {
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
            Connection connection = DriverManager.getConnection(
                    properties.get("DB_URL"), properties.get("DB_USER"), properties.get("DB_PASSWORD"));
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Connection to the DB cannot be established with the current credentials", e);
        }
        return statement;
    }
}
