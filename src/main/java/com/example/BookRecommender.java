package com.example;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

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
        
        // ### input section ###
        int userInput = -1;
        final int MIN = 1;
        final int MAX = 30;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("How many books are you looking for?");
            String input = scanner.nextLine();
            
            try {
                userInput = Integer.parseInt(input);
                if (userInput >= MIN && userInput <= MAX) {
                    break;
                } else {
                    System.out.println("Please enter a number between " + MIN + " and " + MAX + ".");
                } 
            } catch (NumberFormatException e) {
                System.out.println("Input invalid, please enter a valid number.");
            }
        }

        System.out.println("You entered: " + userInput);
        
        int numBooks = userInput;

        System.out.println("Excluding books with less than 50 ratings...");
        System.out.println("These are your top " + numBooks + " books:");

        String filePath = BookRecommender.class.getClassLoader().getResource("goodreadsbooks.csv").getPath();
        BookRecommender recommender = new BookRecommender();
        List<String[]> data = recommender.readCSV(filePath);
        List<String[]> filteredData = new ArrayList<>();

        // ### filtering section ###
        for (String[] row : data.subList(1, data.size())) {
            try {
                int reviews = Integer.parseInt(row [8]);
                double rating = Double.parseDouble(row[3]);
                if (reviews >= 50 && !row[3].isEmpty()) {
                    filteredData.add(row);
                }
            } catch (NumberFormatException | NullPointerException e) {
                continue;
            }
        }

        // ### sorting section ###
        Collections.sort(filteredData, (row1, row2) -> {
            Double rating1 = Double.parseDouble(row1[3]);
            Double rating2 = Double.parseDouble(row2[3]);
            return rating2.compareTo(rating1); 
    });


        // ### output section ###
        for (int i = 0; i < numBooks && i <= 100; i++) {
            String[] row = filteredData.get(i);

            String field1 = row[1];
            String field2 = row[3];

            System.out.println(field1 + ": " + field2);
        }
        
        scanner.close();

    }
}
