package com.gorshkov.db_client.service;

import com.gorshkov.db_client.exception.IllegalQueryException;
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

    public void handle() throws IOException {
        QueryTypeAnalyzer queryTypeAnalyzer = new QueryTypeAnalyzer();
        Command command = queryTypeAnalyzer.analyzeQueryType(query);
        try {
            if (Command.SELECT.equals(command)) {
                handleSelect();
            } else {
                handleNonSelect();
            }
        } catch (SQLException e) {
            throw new IllegalQueryException("The query is illegal.", e);
        }
    }

    private void handleSelect() throws SQLException, IOException {
        ResultSet resultSet = statement.executeQuery(query);
        SelectWriter writer = new SelectWriter();
        writer.write(resultSet);
    }

    private void handleNonSelect() throws SQLException {
        int rowsChanged = statement.executeUpdate(query);
        new NonSelectWriter().write(rowsChanged);
    }
}
