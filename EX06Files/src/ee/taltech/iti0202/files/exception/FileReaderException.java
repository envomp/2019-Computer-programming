package ee.taltech.iti0202.files.exception;

public class FileReaderException extends RuntimeException {
    public FileReaderException(String msg, Throwable e) {
        super(msg, e);
    }
}
