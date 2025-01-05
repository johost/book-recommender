package com.example;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class BookRecommender {
    public List<String[]> readCSV(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        System.out.println("How many books are you looking for?");
        Scanner scanner = new Scanner(System.in);
        int numBooks = scanner.nextInt();

        System.out.println("Excluding books with less than 50 reviews...");

        String filePath = BookRecommender.class.getClassLoader().getResource("goodreadsbooks.csv").getPath();
        BookRecommender recommender = new BookRecommender();
        List<String[]> data = recommender.readCSV(filePath);
        List<String[]> filteredData = new ArrayList<>();

        for (String[] row : data.subList(1, data.size())) {
            int reviews = Integer.parseInt(row [9]);
            if (reviews >= 50) {
                filteredData.add(row);
            }
        }

        for (int i = 0; i < numBooks && i <= 100; i++) {
            String[] row = filteredData.get(i);

            String field1 = row[1];
            String field2 = row[3];

            System.out.println(field1 + ": " + field2);
        }
        
        scanner.close();

        /*
        // Update the path to match your setup
        String filePath = BookRecommender.class.getClassLoader().getResource("goodreadsbooks.csv").getPath();
        BookRecommender recommender = new BookRecommender();

        // Read the CSV and print some information
        List<String[]> data = recommender.readCSV(filePath);

        // Print the number of records and the first few rows
        System.out.println("Total records: " + data.size());
        System.out.println("First row: " + java.util.Arrays.toString(data.get(0)));

        // Print a few sample rows to verify content
        System.out.println("Sample data:");
        for (int i = 1; i <= Math.min(5, data.size() - 1); i++) {
            System.out.println(java.util.Arrays.toString(data.get(i)));
        }
        */
    }
}
