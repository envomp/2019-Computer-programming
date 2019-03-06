package ee.taltech.iti0202.files.exception;

public class FileReaderException extends RuntimeException {

    public FileReaderException() {
        super();
    }

    public FileReaderException(String s) {
        super(s);
    }

    public FileReaderException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FileReaderException(Throwable throwable) {
        super(throwable);
    }

}
