package com.gorshkov.db_client.service;

import com.gorshkov.db_client.exception.ForbiddenCommandException;
import com.gorshkov.db_client.model.CommandEnum;

public class CommandTypeAnalizer {
    public CommandEnum analizeCommandType(String command) throws ForbiddenCommandException {
            String[] parts = command.split(" ");
            if ("SELECT".equals(parts[0]))
                return CommandEnum.SELECT;
            else if ("INSERT".equals(parts[0])) {
                return CommandEnum.INSERT;}
            else if ("UPDATE".equals(parts[0])) {
                return CommandEnum.UPDATE;}
            else if ("DELETE".equals(parts[0])) {
                return CommandEnum.DELETE;
            } else throw new ForbiddenCommandException("Command " + parts[0] + " is forbidden.");
    }
}
