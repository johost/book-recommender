package com.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StatsWriter {
    private String filePath;

    public StatsWriter(String filePath) {
        this.filePath = filePath;
        initializeFile();
    }

    private void initializeFile() {
        File file = new File(filePath);
        if (!fileExists(filePath)) {
            writeToFile("Year,Pages\n");
        }
    }

    public void writeStats(int year, int pages) {
        String data = year + "," + pages + "\n";
        writeToFile(data);
    }

    private boolean fileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    private void writeToFile(String content) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}