package ee.taltech.iti0202.logger.log;

import ee.taltech.iti0202.logger.level.Level;

public class Log {

    String message;
    String tag;
    Level level;

    public Log(String message, String tag, Level level) {
        this.message = message;
        this.tag = tag;
        this.level = level;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTag() {
        return this.tag;
    }

    public Level getLevel() {
        return this.level;
    }
}
