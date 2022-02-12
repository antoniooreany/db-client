package com.gorshkov.db_client.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private List<String> headers = new ArrayList<>();
    private List<Row> rows = new ArrayList<>();

    public List<String> getHeaders() {
        return headers;
    }

    public List<Row> getRows() {
        return rows;
    }
}
