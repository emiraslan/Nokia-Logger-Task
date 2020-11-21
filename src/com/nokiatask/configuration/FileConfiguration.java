package com.nokiatask.configuration;

import com.nokiatask.LogEntry;
import com.nokiatask.LogLevel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileConfiguration extends BaseLoggerConfiguration {
    private String fileNamePattern;
    private int logRotationLineCount;

    private FileConfiguration() {
        super();
        this.fileNamePattern = "log{0}.txt";
        this.logRotationLineCount = 10;
    }

    protected String getFormattedFileName(String fileNumber){
        return this.fileNamePattern.replace("{0}", fileNumber);
    }

    private void createAndFillFile(String content) throws Exception {
        File file = new File(System.getProperty("user.dir") + "/res/log.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.write(content);
        writer.newLine();
        writer.close();
    }

    private int getTheNumberOfLinesInCurrentLog() throws Exception{
        try {
            File file = new File(System.getProperty("user.dir") + "/res/log.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();
            return lines;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private boolean rotateToNewLogFile() throws Exception {
        long count = 0;
        try (Stream<Path> files = Files.list(Paths.get(System.getProperty("user.dir") + "/res/"))) {
            count = files.count();
        }catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }

        String newName = getFormattedFileName(String.valueOf(count));

        File oldFile = new File(System.getProperty("user.dir") + "/res/log.txt");
        File newFile = new File(System.getProperty("user.dir") + "/res/" + newName);

//        if (oldFile.exists())
//            System.out.println("File already exists");

        return oldFile.renameTo(newFile);
    }

    @Override
    public boolean saveLogEntry(LogEntry logEntry) {
        if(!isLoggingEnabled) return false;

        try {
            if (logEntry.getLevel().ordinal() >= this.logLevel.ordinal()){
                if (getTheNumberOfLinesInCurrentLog() >= this.logRotationLineCount) {
                    rotateToNewLogFile();
                }
            }
            createAndFillFile(getFormattedLogString(logEntry));
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
        return true;
    }


    public static class FileBuilder {
        private final Configuration.Builder builder;
        FileConfiguration fileConfiguration;

        FileBuilder(Configuration.Builder builder) {
            this.builder = builder;
            this.fileConfiguration = new FileConfiguration();
        }

        public FileConfiguration.FileBuilder setIsLoggingEnabled(boolean isLoggingEnabled){
            fileConfiguration.isLoggingEnabled = isLoggingEnabled;
            return this;
        }

        public FileConfiguration.FileBuilder setLoggingFormat(String loggingFormat){
            fileConfiguration.loggingFormat = loggingFormat;
            return this;
        }

        public FileConfiguration.FileBuilder setLogLevel(LogLevel logLevel){
            fileConfiguration.logLevel = logLevel;
            return this;
        }


        public FileConfiguration.FileBuilder setFileNamePattern(String fileNamePattern){
            fileConfiguration.fileNamePattern = fileNamePattern;
            return this;
        }

        public FileConfiguration.FileBuilder setLogRotationLineCount(int logRotationLineCount){
            fileConfiguration.logRotationLineCount = logRotationLineCount;
            return this;
        }

        public Configuration.Builder finishFileConfiguration() {
            this.builder.setFileConfiguration();
            return this.builder;
        }
    }

}
