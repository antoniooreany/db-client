package com.gorshkov.db_client.model;

import java.util.List;
import java.util.Map;

public class QueryResult {
    private String tableName;
    private Map<String, List<Object>> fields;

    public QueryResult(String tableName, Map<String, List<Object>> fields) {
        this.tableName = tableName;
        this.fields = fields;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, List<Object>> getFields() {
        return fields;
    }
}
