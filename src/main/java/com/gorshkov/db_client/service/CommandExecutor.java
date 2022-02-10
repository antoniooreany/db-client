package com.gorshkov.db_client.service;

import com.gorshkov.db_client.exception.ForbiddenCommandException;
import com.gorshkov.db_client.model.CommandEnum;
import com.gorshkov.db_client.model.QueryResult;

import java.sql.*;

public class CommandExecutor {
    private final String dbUrl;
    private final String DEFAULT_USER = "user";
    private final String DEFAULT_PASSWORD = "7777777";

    public CommandExecutor(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void execute(String query, QueryResult result) throws SQLException, ForbiddenCommandException {
        CommandTypeAnalizer commandTypeAnalizer = new CommandTypeAnalizer();
        CommandEnum commandEnum = commandTypeAnalizer.analizeCommandType(query);
        if (commandEnum == CommandEnum.SELECT) {
            executeSelect(query, result);
        } else {
            executeNonSelect(query);
        }
    }

    public void executeSelect(String query, QueryResult result) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                dbUrl, DEFAULT_USER, DEFAULT_PASSWORD);) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
//            writeResultToConsole(resultSet);
//            writeResultToFile(resultSet);
            result = new QueryResult()
        }
    }

    public void executeNonSelect(String query) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                dbUrl, DEFAULT_USER, DEFAULT_PASSWORD);) {
            Statement statement = connection.createStatement();
            statement.execute(query);
//            statement.execute(SQL_INSERT_INTO);
//            statement.execute(SQL_DELETE_FROM);
//            writeResultToConsole(resultSet);
        }
    }

}
