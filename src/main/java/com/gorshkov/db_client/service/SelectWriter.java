package com.gorshkov.db_client.service;

import com.gorshkov.db_client.DBTablePrinter;
import io.bretty.console.table.Alignment;
import io.bretty.console.table.Table;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SelectWriter {
    public void write(ResultSet resultSet) throws SQLException, IOException {
        writeToConsole(resultSet);
        writeToHtmlFile(resultSet);
    }

    private void writeToConsole(ResultSet resultSet) throws SQLException {
        //todo
//        Object[][] data = getData(resultSet);
//        Table table = Table.of(data, Alignment.LEFT, 10); // 10-character wide for each column
//        System.out.println(table); // NOTICE: table.toString() is called implicitly
        DBTablePrinter.printResultSet(resultSet);

    }

    private Object[][] getData(ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        int rowCount = getRowCount(resultSet);
        Object[][] data = new Object[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            resultSet.next();
            for (int j = 0; j < columnCount; j++) {
                data[j][i] = resultSet.getObject(j);
            }
        }
        return data;
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

    private void writeToHtmlFile(ResultSet resultSet) throws IOException, SQLException {
        //todo
        File file = new File("C:/Users/GAS_Dell_XPS9310/IdeaProjects/db-client/src/main/resources/report.html");
        boolean newFile = file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));

        writer.write("<table>");
        writer.write("  <tr>");
        String[] columnNames = getColumnNames(resultSet);
        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            writer.write("    <th>");
            writer.write(columnNames[i]);
            writer.write("    </th>");
        }
        writer.write("  <tr>");
        for (int i = 0; i < columnCount; i++) {
            writer.write("    <td>");
            writer.write(resultSet.getString(i + 1));
            writer.write("    </td>");
        }

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
}
