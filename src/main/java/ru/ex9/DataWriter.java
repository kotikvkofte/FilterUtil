package ru.ex9;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

public class DataWriter {
    private String currentPath;
    private final StringParser stringParser;
    private boolean isAdd = false;

    public DataWriter(StringParser stringParser){
        this.stringParser = stringParser;
        try {
            currentPath = new File(".").getCanonicalPath()+"/";
        } catch (IOException e) {
            System.out.printf("Ошибка поиска актуального пути программы: %s\n", e.getMessage());
        }
    }

    public void isAdd(boolean isAdd){
        this.isAdd = isAdd;
    }

    public DataWriter(StringParser stringParser, String path){
        this(stringParser);
        if (!path.isEmpty()){
            currentPath = path+"/";
        }
    }

    public void writeValues(){
        writeDataToFile(stringParser.getIntegerValues(), "integers.txt", isAdd);
        writeDataToFile(stringParser.getFloatValues(), "float.txt", isAdd);
        writeDataToFile(stringParser.getStringValues(), "strings.txt", isAdd);
    }

    public void writeValues(String prefix){
        writeDataToFile(stringParser.getIntegerValues(), prefix + "integers.txt", isAdd);
        writeDataToFile(stringParser.getFloatValues(), prefix + "float.txt", isAdd);
        writeDataToFile(stringParser.getStringValues(), prefix + "strings.txt", isAdd);
    }

    private <T> void writeDataToFile(List<T> values, String fileName, boolean isAdd){
        if (values.isEmpty())
            return;
        File file = new File(currentPath+fileName);
        try{
            if (Files.notExists(file.toPath())) {
                file.createNewFile();
            }
                PrintWriter writer = new PrintWriter(new FileWriter(file, isAdd));
                for (T value: values){
                    writer.println(value.toString());
                }
                writer.close();
        } catch (IOException e) {
            System.out.printf("Ошибка при записи файла %s: %s\n", fileName, e.getMessage());
        }
    }
}
