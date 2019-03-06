package ee.taltech.iti0202.files.exception;

public class FileReaderException extends RuntimeException {

    public FileReaderException(String message, Throwable cause) throws FileReaderException {
        super(message, cause);
    }

}
