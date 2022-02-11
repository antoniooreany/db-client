package com.gorshkov.db_client.service;

public class NonSelectWriter {

    public void write(int rowsChanged) {
        System.out.println("There are " + rowsChanged + " rows changed.");
    }
}
