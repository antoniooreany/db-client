package com.gorshkov.db_client.model;

import java.util.ArrayList;
import java.util.List;

public class Row {
    public List<Object> getValues() {
        return values;
    }

    private List<Object> values = new ArrayList<>();

}
