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
}
