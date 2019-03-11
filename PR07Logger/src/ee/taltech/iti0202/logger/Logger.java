package ee.taltech.iti0202.logger;

import ee.taltech.iti0202.logger.filter.LevelFilter;
import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.formatter.SimpleFormatter;
import ee.taltech.iti0202.logger.level.Level;
import ee.taltech.iti0202.logger.log.Log;

public abstract class Logger {

    String tag;
    Level level;
    LogFormatter formatter;
    LogFilter filter;
    String message;

    /**
     * Creates logger that logs messages with Level.Warning or higher.
     *
     * @param tag unique identifier of given logger.
     * @see ee.taltech.iti0202.logger.level.Level
     */
    public Logger(String tag) {
        this(tag, Level.WARNING);
    }

    /**
     * Creates logger that logs from given level.
     *
     * @param tag   unique identifier of given logger.
     * @param level minimum logging level.
     * @see ee.taltech.iti0202.logger.level.Level
     */
    public Logger(String tag, Level level) {
        this.tag = tag;
        this.level = level;
        this.filter = new LevelFilter(() -> level);
        this.formatter = new SimpleFormatter();
    }

    /**
     * Creates logger that logs from given level.
     *
     * @param tag       unique identifier of given logger.
     * @param level     minimum logging level.
     * @param formatter custom formatter.
     * @see ee.taltech.iti0202.logger.level.Level
     */
    public Logger(String tag, Level level, LogFormatter formatter) {
        this.tag = tag;
        this.level = level;
        this.formatter = formatter;
        this.filter = new LevelFilter(() -> level);
    }

    /**
     * Create logger with custom filter.
     *
     * @param tag       unique identifier of given logger.
     * @param filter    custom filter
     * @param formatter custom formatter.
     */
    public Logger(String tag, LogFilter filter, LogFormatter formatter) {
        this.tag = tag;
        this.filter = filter;
        this.formatter = formatter;
    }


    /**
     * Logs the message.
     */
    public final void log(Level level, String message) {
        this.level = level;
        this.message = message;
        this.filter = new LevelFilter(() -> level);
        this.formatter = new SimpleFormatter();
        writeLog(message);
    }

    /**
     * Abstract method that is called with formatted message.
     * This message has passed user provided filter and should be logged.
     */
    protected abstract void writeLog(String message);

    /**
     * Creates log with Level.SEVERE
     */
    public final void severe(String message) {

        this.message = message;
        this.level = Level.SEVERE;
        logWriter(message);
    }

    private void logWriter(String message) {
        Log log = new Log(message, tag, level);
        if (filter.isLoggable(log)) {
            writeLog(formatter.format(log));
        }
    }

    /**
     * Creates log with Level.ERROR
     */
    public final void error(String message) {
        this.message = message;
        this.level = Level.ERROR;
        logWriter(message);
    }

    /**
     * Creates log with Level.WARNING
     */
    public final void warning(String message) {
        this.message = message;
        this.level = Level.WARNING;
        logWriter(message);
    }

    /**
     * Creates log with Level.INFO
     */
    public final void info(String message) {
        this.message = message;
        this.level = Level.INFO;
        logWriter(message);
    }

    /**
     * Creates log with Level.DEBUG
     */
    public final void debug(String message) {
        this.message = message;
        this.level = Level.DEBUG;
        logWriter(message);
    }
}
