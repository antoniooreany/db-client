package com.gorshkov.db_client.service;

import com.gorshkov.db_client.model.CommandEnum;

import java.sql.SQLException;

public class CommandTypeManager {

    private CommandExecutor commandExecutor;
    private CommandEnum commandEnum;
    private String dbUrl;

    public void manageCommandByType(String query) throws SQLException {
        CommandExecutor commandExecutor = new CommandExecutor(dbUrl);
        Result result;
        if (commandEnum == CommandEnum.SELECT) {
            result = commandExecutor.executeSelect(query);

        } else {
            result = commandExecutor.executeNonSelect(query);
        }
    }
}
