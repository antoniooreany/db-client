package com.gorshkov.db_client.service;

import com.gorshkov.db_client.model.Command;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryHandler {

    private final Statement statement;
    private final String query;

    public QueryHandler(Statement statement, String query) {
        this.statement = statement;
        this.query = query;
    }

    public void handle() throws SQLException, IOException {
        String[] part = query.split(" ");
        Command command = Command.getCommand(part[0]);
        if (command.equals(Command.SELECT)) {
            handleSelect();
        } else handleNonSelect(); // todo put another: insert, update, delete
    }

    private void handleSelect() throws SQLException, IOException {
        ResultSet resultSet = statement.executeQuery(query);
        SelectWriter writer = new SelectWriter();
        writer.write(resultSet);
    }

    private void handleNonSelect() throws SQLException {
        int columnsUpdated = statement.executeUpdate(query);
        System.out.println("Updated: " + columnsUpdated + " columns.");

    }
}
