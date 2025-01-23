package ru.ex9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private static final String RESOURCES = "";
    private List<String> lines;

    public FileReader(String[] fileNames){
        lines = new ArrayList<>();
        for (String filename: fileNames) {
            final Path path = Paths.get(RESOURCES + filename);
            try {
                lines.addAll(Files.readAllLines(path));
            } catch (IOException e) {
                System.out.printf("Ошибка при чтении файла %s: %s\n", filename, e.getMessage());
            }
        }
    }

    public List<String> getReadLines(){
        return lines;
    }
}
