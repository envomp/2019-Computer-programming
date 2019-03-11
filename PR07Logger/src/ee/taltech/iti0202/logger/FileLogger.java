package ee.taltech.iti0202.logger;

import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.level.Level;

public class FileLogger extends Logger {

    public FileLogger(String tag, String logFilePath) {
        // super + do something with logFilePath
        super(tag);
    }

    public FileLogger(String tag, String logFilePath, Level level) {
        // super + do something with logFilePath
        super(tag, level);
    }

    public FileLogger(String tag, String logFilePath, Level level, LogFormatter formatter) {
        // super + do something with logFilePath
        super(tag, level, formatter);
    }

    public FileLogger(String tag, String logFilePath, LogFilter filter, LogFormatter formatter) {
        // super + do something with logFilePath
        super(tag, filter, formatter);
    }

    @Override
    protected void writeLog(String message) {
        // append to log file if exists
        // if doesn't exist create file
    }
}
