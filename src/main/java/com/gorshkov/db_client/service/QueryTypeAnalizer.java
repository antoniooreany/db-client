package com.gorshkov.db_client.service;

import com.gorshkov.db_client.exception.IllegalQueryException;
import com.gorshkov.db_client.model.Command;

public class QueryTypeAnalizer {
    public Command analizeQueryType(String query) {
        if (query.toUpperCase().startsWith(Command.SELECT.name())) {
            return Command.SELECT;
        } else if (query.toUpperCase().startsWith(Command.INSERT.name())) {
            return Command.INSERT;
        } else if (query.toUpperCase().startsWith(Command.UPDATE.name())) {
            return Command.UPDATE;
        } else if (query.toUpperCase().startsWith(Command.DELETE.name())) {
            return Command.DELETE;
        } else throw new IllegalQueryException("The query is illegal.");
    }
}
