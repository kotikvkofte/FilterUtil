package ru.ex9;


import org.apache.commons.cli.*;

import java.io.IOException;

public class ArgumentParser {
    private CommandLine cmd;

    public ArgumentParser(String[] args){
        DefaultParser parser = new DefaultParser();
        try {
            cmd = parser.parse(loadOptions(), args);
        } catch (ParseException e) {
            System.out.println("Ошибка при парсинге командной строки: " + e.getMessage());
        }
    }

    public boolean isAdd(){
        return cmd.hasOption("a");
    }

    public boolean isFullStat(){
        return cmd.hasOption("f");
    }

    public boolean isShortStat(){
        return cmd.hasOption("s");
    }

    public String[] getFileNames(){
        return cmd.getArgs();
    }

    public String getPrefix(){
        if (cmd.hasOption("p")){
            return cmd.getOptionValue("p");
        }
        return "";
    }

    public String getPath(){
        if (cmd.hasOption("o")){
            return cmd.getOptionValue("o");
        }
        return "";
    }

    private Options loadOptions(){
        Options options= new Options();

        Option resulrDirOption = Option.builder("o")
                .hasArg()
                .argName("result directory")
                .desc("путь для результатов")
                .build();
        options.addOption(resulrDirOption);

        Option prefixOption = Option.builder("p")
                .hasArg()
                .argName("prefix")
                .desc("префикс имен выходных файлов")
                .build();
        options.addOption(prefixOption);

        Option addOption = Option.builder("a")
                .desc("режим добавления в существующие файлы")
                .build();
        options.addOption(addOption);

        OptionGroup filterGroup = new OptionGroup();

        Option shortFilterOption = Option.builder("s")
                .desc("краткая статистика")
                .build();
        filterGroup.addOption(shortFilterOption);

        Option longFilterOption = Option.builder("f")
                .desc("полная статистика")
                .build();
        filterGroup.addOption(longFilterOption);

        options.addOptionGroup(filterGroup);

        return options;
    }
}
