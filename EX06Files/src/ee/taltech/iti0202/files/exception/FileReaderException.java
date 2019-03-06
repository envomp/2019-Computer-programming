package ee.taltech.iti0202.files.exception;

import org.testing.internal.tread.ThreadTimeoutException;

public class FileReaderException extends RuntimeException {

    public FileReaderException(String message, Throwable cause) throws FileReaderException {
        //super("No such file");
        try {
            throw new FileReaderException(cause);
        } catch (StackOverflowError e) {
            throw new FileReaderException(cause);
        } catch (ThreadTimeoutException e) {
            throw this;
        }

    }

    public FileReaderException(Throwable t) {
        throw new FileReaderException("No such file", t);
    }

}
