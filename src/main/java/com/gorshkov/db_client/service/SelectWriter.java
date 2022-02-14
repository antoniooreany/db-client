package com.gorshkov.db_client.service;

import com.gorshkov.db_client.model.Row;
import com.gorshkov.db_client.model.Table;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SelectWriter {

    public void write(ResultSet resultSet) throws SQLException, IOException {
        Table table = getTable(resultSet);
        writeToConsole(table);
        writeToHtmlFile(table);
    }

    private void writeToConsole(Table table) {
        List<String> headers = table.getHeaders();
        List<Row> rows = table.getRows();

        for (String header : headers) {
            System.out.print(header + " | ");
        }
        System.out.println();
        System.out.println("----------------------------------------------------");

        for (Row row : rows) {
            for (Object value : row.getValues()) {
                System.out.print(value + " | ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------");
    }

    private void writeToHtmlFile(Table table) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/report.html"));) {
            List<String> headers = table.getHeaders();
            List<Row> rows = table.getRows();

            StringBuilder builder = new StringBuilder("<table>\n");
            builder.append("    <tr>\n");
            for (String header : headers) {
                builder.append("        <th>").append(header).append("</th>\n");
            }
            builder.append("    <tr>\n");

            for (Row row : rows) {
                for (int i = 0; i < headers.size(); i++) {
                    builder.append("        <td>").append(row.getValues().get(i)).append("</td>\n");
                }
                builder.append("    <tr>\n");
            }

            builder.append("</table>");
            writer.write(builder.toString());
            writer.flush();
        }
    }

    private Table getTable(ResultSet resultSet) throws SQLException {
        Table table = new Table();
        List<String> columns = table.getHeaders();
        List<Row> rows = table.getRows();

        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            columns.add(resultSet.getMetaData().getColumnName(i + 1));
        }

        while (resultSet.next()) {
            Row row = new Row();
            List<Object> values = row.getValues();
            for (String column : columns) {
                Object value = resultSet.getObject(column);
                values.add(value);
            }
            rows.add(row);
        }
        return table;
    }
}
