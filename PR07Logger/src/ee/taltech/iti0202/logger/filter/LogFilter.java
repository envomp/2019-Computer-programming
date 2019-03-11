package ee.taltech.iti0202.logger.filter;

import ee.taltech.iti0202.logger.log.Log;

public interface LogFilter {
    boolean isLoggable(Log log);
}
