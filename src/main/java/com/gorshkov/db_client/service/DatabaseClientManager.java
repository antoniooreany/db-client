package com.gorshkov.db_client.service;

import com.gorshkov.db_client.exception.ForbiddenCommandException;
import com.gorshkov.db_client.model.CommandEnum;
import com.gorshkov.db_client.model.QueryResult;

import java.sql.SQLException;

public class DatabaseClientManager {
    public void start() throws SQLException, ForbiddenCommandException {
        SettingsReader settingsReader = new SettingsReader();
        String dbUrl = settingsReader.getDbUrl();

        ConsoleReader consoleReader = new ConsoleReader();
        String query = consoleReader.getInput();

        CommandTypeAnalizer commandTypeAnalizer = new CommandTypeAnalizer();
        CommandEnum commandEnum = commandTypeAnalizer.analizeCommandType(query);

        CommandExecutor commandExecutor = new CommandExecutor(dbUrl);
        QueryResult result = new QueryResult();
        commandExecutor.execute(query, result);

        ConsoleWriter consoleWriter = new ConsoleWriter();


    }
}
