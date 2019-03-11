package ee.taltech.iti0202.logger.filter;

import ee.taltech.iti0202.logger.level.LevelProvider;
import ee.taltech.iti0202.logger.log.Log;

public class LevelFilter implements LogFilter {

    private LevelProvider levelProvider;

    public LevelFilter(LevelProvider levelProvider) {
        this.levelProvider = levelProvider;
    }

    @Override
    public boolean isLoggable(Log log) {
        return levelProvider.getLevel().getValue() <= log.getLevel().getValue(); // should log if levelProviders.log.value is less or equal to Log.level.value
    }
}
