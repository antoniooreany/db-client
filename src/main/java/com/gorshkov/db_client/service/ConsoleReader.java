package com.gorshkov.db_client.service;

import java.util.Scanner;

public class ConsoleReader {
    public String getInput() {
        System.out.println("Enter your query: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
