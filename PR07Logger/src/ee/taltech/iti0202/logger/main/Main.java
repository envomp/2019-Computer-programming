package ee.taltech.iti0202.logger.main;

import ee.taltech.iti0202.logger.ConsoleLogger;
import ee.taltech.iti0202.logger.FileLogger;
import ee.taltech.iti0202.logger.Logger;
import ee.taltech.iti0202.logger.filter.LogFilter;
import ee.taltech.iti0202.logger.formatter.LogFormatter;
import ee.taltech.iti0202.logger.formatter.SimpleFormatter;
import ee.taltech.iti0202.logger.formatter.UppercaseFormatter;
import ee.taltech.iti0202.logger.level.Level;

import java.util.Date;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String className = Main.class.getName();

        System.out.println("Simple demo, logs warning, but not info");
        Logger logger = new ConsoleLogger(className);
        testLogger(logger);
        System.out.println();

        System.out.println("Log nothing");
        logger = new ConsoleLogger("I log nothing", Level.OFF);
        testLogger(logger);
        System.out.println();

        System.out.println("Custom formatter in uppercase");
        logger = new ConsoleLogger("CAPS LOCK!", Level.DEBUG, new UppercaseFormatter());
        testLogger(logger);
        System.out.println();

        System.out.println("Custom formatter with dates");
        LogFormatter dateFormatter = log -> {
            var logFormatter = new SimpleFormatter();
            return String.format("%tF %<tT\t%s", new Date(), logFormatter.format(log));
        };
        logger = new ConsoleLogger("date", Level.DEBUG, dateFormatter);
        testLogger(logger);
        System.out.println();

        System.out.println("Custom random filter");
        Random random = new Random();
        LogFilter randomFilter = log -> random.nextBoolean();
        logger = new ConsoleLogger("", randomFilter, dateFormatter);
        testLogger(logger);
        System.out.println();

        logger = new FileLogger(className, "log.txt");
        testLogger(logger);
    }

    private static void testLogger(Logger logger) {
        logger.severe("Severe message");
        logger.error("Error message");
        logger.warning("Warning message");
        logger.info("Info message");
        logger.debug("Debug message");
    }
}
