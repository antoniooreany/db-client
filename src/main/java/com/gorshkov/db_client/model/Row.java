package com.gorshkov.db_client.model;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private final List<Object> values = new ArrayList<>();

    public List<Object> getValues() {
        return values;
    }

}
