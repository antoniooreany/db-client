package com.gorshkov.db_client;

public class CommandExecutor {
    public void executeCommand(String input) {
        CommandTypeAnalizer commandTypeAnalizer = new CommandTypeAnalizer();
        CommandEnum commandEnum = commandTypeAnalizer.analizeCommand(input);

        if (commandEnum == CommandEnum.SELECT) {
            SelectConsoleWriter selectConsoleWriter = new SelectConsoleWriter();
            selectConsoleWriter.write();

            SelectFileWriter selectFileWriter = new SelectFileWriter();
            selectFileWriter.write();
        } else {
            ConsoleWriter consoleWriter = new ConsoleWriter();
            consoleWriter.write();
        }
    }
}
