package ee.taltech.iti0202.logger;

import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.level.Level;

public class ConsoleLogger extends Logger {

    public ConsoleLogger(String tag) {
        super(tag);
    }

    public ConsoleLogger(String tag, Level level) {
        super(tag, level);
    }

    public ConsoleLogger(String tag, Level level, LogFormatter formatter) {
        // call also super here
        super(tag, level, formatter);
    }

    public ConsoleLogger(String tag, LogFilter filter, LogFormatter formatter) {
        // same thing.
        super(tag, filter, formatter);
    }

    @Override
    protected void writeLog(String message) {
        // print this message to the console.
        System.out.println(message);
    }

}
