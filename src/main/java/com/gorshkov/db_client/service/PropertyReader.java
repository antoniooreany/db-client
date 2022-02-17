package com.gorshkov.db_client.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PropertyReader {
    private static HashMap<String, String> properties = new HashMap<>();

    public static HashMap<String, String> getProperties() {
        String path = "C:/Users/GAS_Dell_XPS9310/IdeaProjects/db-client/src/main/resources/application.properties";
        readProperties(path);
        return properties;
    }

    private static void readProperties(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                properties.put(parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No properties file found at: " + path);
        } catch (IOException e) {
            throw new RuntimeException("Something's wrong with your properties file at: " + path);
        }
    }
}
