package com.gorshkov.db_client;

public class DatabaseClient {
    public void start() {
        SettingsReader settingsReader = new SettingsReader();
        settingsReader.readSettings();

        InputHandler inputHandler = new InputHandler();
        String input = inputHandler.handleInput();

        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.executeCommand(input);


    }
}
