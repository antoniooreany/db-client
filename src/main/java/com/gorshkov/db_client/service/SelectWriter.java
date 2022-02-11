package com.gorshkov.db_client.service;

import com.gorshkov.db_client.model.Row;
import com.gorshkov.db_client.model.Table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SelectWriter {

    private static int reportCount;

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
        for (Row row : rows) {
            List<Object> values = row.getValues();
            for (Object value : values) {
                System.out.print(value + " | ");
            }
            System.out.println();
        }
    }

    private void writeToHtmlFile(Table table) throws IOException {
        File file = new File("./src/main/resources/report" + reportCount++ + ".html");
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("<table>\n");
        writer.write("    <tr>\n");
        List<String> columnNames = table.getHeaders();
        for (String columnName : columnNames) {
            writer.write("        <th>");
            writer.write(columnName);
            writer.write("</th>\n");
        }

        writer.write("    <tr>\n");

        for (Row row : table.getRows()) {
            for (Object value : row.getValues()) {
                writer.write("        <td>");
                writer.write(String.valueOf(value));
                writer.write("</td>\n");
            }
            writer.write("    <tr>\n");
        }
        writer.write("</table>");
        writer.flush();
    }

    private Table getTable(ResultSet resultSet) throws SQLException {
        String[] columnNames = getColumnNames(resultSet);
        Table table = new Table();

        table.setHeaders(Arrays.asList(columnNames));

        List<Row> rows = table.getRows();
        while (resultSet.next()) {
            Row row = new Row();
            List<Object> values = row.getValues();
            for (String columnName : columnNames) {
                Object columnValue = resultSet.getObject(columnName);
                values.add(columnValue);
            }
            rows.add(row);
        }
        return table;
    }

    private String[] getColumnNames(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = metaData.getColumnName(i + 1);
        }
        return columnNames;
    }

    public int getRowCount(ResultSet resultSet) throws SQLException {
        int size;
        try {
            resultSet.last();
            size = resultSet.getRow();
            resultSet.beforeFirst();
        } catch (SQLException ex) {
            return 0;
        }
        return size;
    }
}


