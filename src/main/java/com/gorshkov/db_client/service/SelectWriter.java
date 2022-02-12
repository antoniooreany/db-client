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
    private static final String DELIMITER = " | ";
    private static final String SHORT_PATH_NAME = "./src/main/resources/report";
    private static final String HTML = ".html";
    private static final String TABLE_OPEN = "<table>\n";
    private static final String TABLE_CLOSE = "</table>";
    private static final String TR_OPEN = "    <tr>\n";
    private static final String TH_OPEN = "        <th>";
    private static final String TH_CLOSE = "</th>\n";
    private static final String TD_OPEN = "        <td>";
    private static final String TD_CLOSE = "</td>\n";

    public void write(ResultSet resultSet) throws SQLException, IOException {
        Table table = getTable(resultSet);

        writeToConsole(table);
        writeToHtmlFile(table);
    }

    private void writeToConsole(Table table) {

        List<String> headers = table.getHeaders();
        List<Row> rows = table.getRows();

        for (String header : headers) {
            System.out.print(header + DELIMITER);
        }
        System.out.println();
        for (Row row : rows) {
            List<Object> values = row.getValues();
            for (Object value : values) {
                System.out.print(value + DELIMITER);
            }
            System.out.println();
        }
    }

    private void writeToHtmlFile(Table table) throws IOException {
        File file = new File(SHORT_PATH_NAME
                + reportCount++
                + HTML);
        boolean newFile = file.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));) {
            writer.write(TABLE_OPEN);
            writer.write(TR_OPEN);
            List<String> columnNames = table.getHeaders();
            for (String columnName : columnNames) {
                writer.write(TH_OPEN);
                writer.write(columnName);
                writer.write(TH_CLOSE);
            }

            writer.write(TR_OPEN);

            for (Row row : table.getRows()) {
                for (Object value : row.getValues()) {
                    writer.write(TD_OPEN);
                    writer.write(String.valueOf(value));
                    writer.write(TD_CLOSE);
                }
                writer.write(TR_OPEN);
            }
            writer.write(TABLE_CLOSE);
            writer.flush();
        }
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
                Object value = resultSet.getObject(columnName);
                values.add(value);
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


