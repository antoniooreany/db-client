package com.gorshkov.db_client.service;

public class NonSelectWriter {

    public void write(int columnsUpdated) {
        System.out.println("Updated: " + columnsUpdated + " columns.");
    }
}
