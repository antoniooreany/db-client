package com.gorshkov.db_client.service;

public class HtmlGenerator {
    public String generateHtml(Result result) {
        String[] columnNames = getColumnNames(resultSet);
        StringBuilder headers = getHeadersToHtml(resultSet, columnNames);
        String dataToHtml = getDataToHtml(resultSet);

        StringBuilder builder = new StringBuilder(TABLE_OPEN_TAG);
        builder.append(headers);
        builder.append(dataToHtml);
        builder.append(TABLE_CLOSE_TAG);
    }
}
