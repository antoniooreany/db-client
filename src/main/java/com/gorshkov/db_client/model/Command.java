package com.gorshkov.db_client.model;

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
        if ("SELECT".equalsIgnoreCase(name)) {
            return SELECT;
        } else if ("INSERT".equalsIgnoreCase(name)) {
            return INSERT;
        } else if ("UPDATE".equalsIgnoreCase(name)) {
            return UPDATE;
        } else if ("DELETE".equalsIgnoreCase(name)) {
            return DELETE;
        } else throw new IllegalArgumentException("The name " + name + " of the command is not in the legal list");
    }


}
