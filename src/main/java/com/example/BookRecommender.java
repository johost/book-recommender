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

        // how many books?
        int bookInput = -1;
        final int bookMIN = 1;
        final int bookMAX = 30;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("How many books are you looking for?");
            String input = scanner.nextLine();
            
            try {
                bookInput = Integer.parseInt(input);
                if (bookInput >= bookMIN && bookInput <= bookMAX) {
                    break;
                } else {
                    System.out.println("Please enter a number between " + bookMIN + " and " + bookMAX + ".");
                } 
            } catch (NumberFormatException e) {
                System.out.println("Input invalid, please enter a valid number.");
            }
        }

        // what rating?
        float ratingInput = -1;
        final float ratingMIN = 1;
        final float ratingMAX = 5;

        while (true) {
            System.out.println("Which average rating should the book have at minimum? \n- Valid ratings are between 1.0 and 5.0. \n- You can use decimal values such as '4.0'. \n- Please use the '.' and not a ','.");
            String input = scanner.nextLine();
            
            try {
                ratingInput = Float.parseFloat(input);
                if (ratingInput >= ratingMIN && ratingInput <= ratingMAX) {
                    break;
                } else {
                    System.out.println("Please enter a number between " + ratingMIN + " and " + ratingMAX + ".");
                } 
            } catch (NumberFormatException e) {
                System.out.println("Input invalid, please enter a valid number.");
            }
        }

        System.out.println("You entered: " + ratingInput);

        // how old maximum?
        int yearInput = -1;
        final int yearMIN = 1900;
        final int yearMAX = 2020;

        while (true) {
            System.out.println("How old should the books be maxmium? \n- The oldest books are from 1900 \n- The newest books are from 2020");
            String input = scanner.nextLine();
            
            try {
                yearInput = Integer.parseInt(input);
                if (yearInput >= yearMIN && yearInput <= yearMAX) {
                    break;
                } else {
                    System.out.println("Please enter a number between " + yearMIN + " and " + yearMAX + ".");
                } 
            } catch (NumberFormatException e) {
                System.out.println("Input invalid, please enter a valid number.");
            }
        }

        System.out.println("You entered: " + yearInput);

        // how many pages maximum?
        int pagesInput = -1;
        final int pagesMIN = 1;
        final int pagesMAX = 6576;

        while (true) {
            System.out.println("How many pages should the books have maxmium? \n- Please start from 1 minimum \n- The longest book in the dataset has 6576 pages");
            String input = scanner.nextLine();
            
            try {
                pagesInput = Integer.parseInt(input);
                if (pagesInput >= pagesMIN && pagesInput <= pagesMAX) {
                    break;
                } else {
                    System.out.println("Please enter a number between " + pagesMIN + " and " + pagesMAX + ".");
                } 
            } catch (NumberFormatException e) {
                System.out.println("Input invalid, please enter a valid number.");
            }
        }

        System.out.println("You entered: " + pagesInput);

        // continue processing
        int numBooks = bookInput;

        System.out.println("Excluding books with less than 50 ratings...");
        System.out.println("I recommend you to check out the following " + numBooks + " books, having a minimum average rating of " + ratingInput + ":");

        String filePath = BookRecommender.class.getClassLoader().getResource("goodreadsbooks.csv").getPath();
        BookRecommender recommender = new BookRecommender();
        List<String[]> data = recommender.readCSV(filePath);
        List<String[]> filteredData = new ArrayList<>();

        // ### filtering section ###
        for (String[] row : data.subList(1, data.size())) {
            try {
                float reviews = Float.parseFloat(row [8]);
                float rating = Float.parseFloat(row[3]);
                if (reviews >= 50 && !row[3].isEmpty() && rating >= ratingInput) {
                    filteredData.add(row);
                }
            } catch (NumberFormatException | NullPointerException e) {
                continue;
            }
        }

        // randomize by shuffling and reduce by creating sublist
        Collections.shuffle(filteredData);
        List<String[]> reducedData = filteredData.subList(0, Math.min(bookInput, filteredData.size()));

        // ### sorting section ###
        Collections.sort(reducedData, (row1, row2) -> {
            Double rating1 = Double.parseDouble(row1[3]);
            Double rating2 = Double.parseDouble(row2[3]);
            return rating2.compareTo(rating1); 
    });

        // ### output section ###
        for (String[] row : reducedData) {
            String title = row[1];
            String rating = row[3];

            System.out.println(title + ": " + rating);
        }
        
        scanner.close();

    }
}
