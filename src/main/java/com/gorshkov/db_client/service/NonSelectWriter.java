package com.gorshkov.db_client.service;

public class NonSelectWriter {
//  INSERT INTO db.persons VALUES (3, 'value2', 'value3', 'value4', 'value5');
//  UPDATE db.persons SET PersonID = 55 WHERE PersonID = 3;
//  DELETE FROM db.persons WHERE PersonID = 55;
    public void write(int rowsChanged) {
        System.out.println("There are " + rowsChanged + " rows changed.");
    }
}
