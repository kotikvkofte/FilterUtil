package ru.ex9;

public class UtilityProcessor {
    private final ArgumentParser argumentParser;
    private final StringParser stringParser;
    private final DataWriter dataWriter;

    public UtilityProcessor(String[] args) {
        argumentParser = new ArgumentParser(args);
        FileReader fileReader = new FileReader(argumentParser.getFileNames());
        stringParser = new StringParser(fileReader.getReadLines());
        stringParser.parse();
        dataWriter = new DataWriter(stringParser, argumentParser.getPath());
    }

    public void run(){
        if (argumentParser.isShortStat()){
            System.out.println(getShortStat());
        } else if (argumentParser.isFullStat()) {
            System.out.println(getFullStat());
        }

        dataWriter.isAdd(argumentParser.isAdd());
        dataWriter.writeValues(argumentParser.getPrefix());
    }

    private String getShortStat(){
        int intCount = stringParser.getIntegerValues().size();
        int stringCount = stringParser.getStringValues().size();
        int floatCount = stringParser.getFloatValues().size();

        String resultString = "";

        resultString += (intCount > 0) ? "Целые числа: " + intCount + "\n" : "";
        resultString += (floatCount > 0) ? "Вещественные числа: " + floatCount + "\n" : "";
        resultString += (stringCount > 0) ? "Строки: " + stringCount + "\n" : "";

        return resultString;
    }

    private String getFullStat(){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(intStat());
        stringBuilder.append(floatStat());
        stringBuilder.append(stringStat());

        return stringBuilder.toString();
    }

    private String intStat(){
        int intCount = stringParser.getIntegerValues().size();
        StringBuilder stringBuilder = new StringBuilder();
        if (intCount > 0){
            stringBuilder.append("Целые числа:\n");
            stringBuilder.append("Количество - ").append(intCount).append("\n");
            Integer intMinValue = stringParser.getIntegerValues().stream().mapToInt(v -> v).min().getAsInt();
            Integer intMaxValue = stringParser.getIntegerValues().stream().mapToInt(v -> v).max().getAsInt();
            stringBuilder.append("Минимальное - ").append(intMinValue).append("\n");
            stringBuilder.append("Максимальное - ").append(intMaxValue).append("\n");

            Integer intSum = stringParser.getIntegerValues().stream().mapToInt(v -> v).sum();
            stringBuilder.append("Сумма - ").append(intSum).append("\n");

            Double intAverage = stringParser.getIntegerValues().stream().mapToInt(v -> v).average().getAsDouble();
            stringBuilder.append("Среднее - ").append(intAverage).append("\n\n");
        }
        return stringBuilder.toString();
    }

    private String floatStat(){
        int floatCount = stringParser.getFloatValues().size();
        StringBuilder stringBuilder = new StringBuilder();
        if (floatCount > 0){
            stringBuilder.append("Вещественные числа:\n");
            stringBuilder.append("Количество - ").append(floatCount).append("\n");
            Double floatMinValue = stringParser.getFloatValues().stream().mapToDouble(v -> v).min().getAsDouble();
            Double floatMaxValue = stringParser.getFloatValues().stream().mapToDouble(v -> v).max().getAsDouble();
            stringBuilder.append("Минимальное - ").append(floatMinValue).append("\n");
            stringBuilder.append("Максимальное - ").append(floatMaxValue).append("\n");

            Double floatSum = stringParser.getFloatValues().stream().mapToDouble(v -> v).sum();
            stringBuilder.append("Сумма - ").append(floatSum).append("\n");

            Double floatAverage = stringParser.getFloatValues().stream().mapToDouble(v -> v).average().getAsDouble();
            stringBuilder.append("Среднее - ").append(floatAverage).append("\n\n");
        }
        return stringBuilder.toString();
    }

    private String stringStat(){
        int stringCount = stringParser.getStringValues().size();
        StringBuilder stringBuilder = new StringBuilder();
        if (stringCount > 0){
            stringBuilder.append("Строки:\n");
            stringBuilder.append("Количество - ").append(stringCount).append("\n");
            Integer minStringLength = stringParser.getStringValues().stream().mapToInt(String::length).min().getAsInt();
            Integer maxStringLength = stringParser.getStringValues().stream().mapToInt(String::length).max().getAsInt();
            stringBuilder.append("Размер самой короткой строки - ").append(minStringLength).append("\n");
            stringBuilder.append("Размер самой длинной строки - ").append(maxStringLength).append("\n");
        }
        return stringBuilder.toString();
    }
}
