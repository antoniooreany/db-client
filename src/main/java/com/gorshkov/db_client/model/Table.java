package com.gorshkov.db_client.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
    List<String> headers;
    List<Row> rows = new ArrayList<>();

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
