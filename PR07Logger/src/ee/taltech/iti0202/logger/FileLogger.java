package ee.taltech.iti0202.logger;

import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.level.Level;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLogger extends Logger {
    String logFilePath;

    public FileLogger(String tag, String logFilePath) {
        // super + do something with logFilePath
        super(tag);
        this.logFilePath = logFilePath;
    }

    public FileLogger(String tag, String logFilePath, Level level) {
        // super + do something with logFilePath
        super(tag, level);
        this.logFilePath = logFilePath;
    }

    public FileLogger(String tag, String logFilePath, Level level, LogFormatter formatter) {
        // super + do something with logFilePath
        super(tag, level, formatter);
        this.logFilePath = logFilePath;
    }

    public FileLogger(String tag, String logFilePath, LogFilter filter, LogFormatter formatter) {
        // super + do something with logFilePath
        super(tag, filter, formatter);
        this.logFilePath = logFilePath;
    }

    @Override
    protected void writeLog(String message) {
        // append to log file if exists
        // if doesn't exist create file
        try {
            Files.write(Paths.get(logFilePath), (message + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            try {
                Files.write(Paths.get(logFilePath), (message + "\n").getBytes(), StandardOpenOption.CREATE_NEW);
            } catch (IOException w) {
                System.out.println("You really fucked up...");
            }
        }
    }
}
