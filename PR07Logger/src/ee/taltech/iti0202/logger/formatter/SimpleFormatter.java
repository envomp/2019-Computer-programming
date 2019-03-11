package ee.taltech.iti0202.logger.formatter;

import ee.taltech.iti0202.logger.log.Log;

public class SimpleFormatter implements LogFormatter {

    @Override
    public String format(Log log) {
        return String.format("%s\t%s\t%s", log.getLevel().getName(), log.getTag(), log.getMessage());
    }

}
