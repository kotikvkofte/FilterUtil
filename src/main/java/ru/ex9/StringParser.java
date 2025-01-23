package ru.ex9;

import java.util.ArrayList;
import java.util.List;

public class StringParser {
    private final List<String> lines;
    private final List<Integer> intValues = new ArrayList<>();
    private final List<String> stringValues = new ArrayList<>();
    private final List<Float> floatValues = new ArrayList<>();

    public StringParser(List<String> lines){
        this.lines = lines;
    }

    public void parse(){
        for (var line: lines){
            parseLine(line);
        }
    }

    public List<Integer> getIntegerValues(){
        return intValues;
    }

    public List<Float> getFloatValues(){
        return floatValues;
    }

    public List<String> getStringValues()
    {
        return stringValues;
    }

    private void parseLine(String line){
        try {
            intValues.add(Integer.parseInt(line));
        } catch (NumberFormatException e){
            try {
                floatValues.add(Float.parseFloat(line));
            } catch (NumberFormatException ex){
                stringValues.add(line);
            }
        }
    }
}
