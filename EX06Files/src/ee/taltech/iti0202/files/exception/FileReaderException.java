package ee.taltech.iti0202.files.exception;

public class FileReaderException extends RuntimeException {

    public FileReaderException(String message, Throwable cause) throws FileReaderException {
        throw new FileReaderException(cause);
    }

    public FileReaderException(Throwable t) {
        super("No such file", t);
    }

}
