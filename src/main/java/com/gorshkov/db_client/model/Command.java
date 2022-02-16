package com.gorshkov.db_client.model;

import java.util.Arrays;

public enum Command {
    SELECT("SELECT"),
    INSERT("INSERT"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String name;

    Command(String name) {
        this.name = name;
    }

    public static Command getCommand(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The name " + name + " of the command is not in the legal list"));
    }


}
