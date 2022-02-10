package com.gorshkov.db_client.service;

public class SettingsReader {
    public String getDbUrl() {
        return System.getenv("DB_URL");
    }
}
