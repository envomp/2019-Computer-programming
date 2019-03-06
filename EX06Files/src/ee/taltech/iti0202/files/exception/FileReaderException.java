package ee.taltech.iti0202.files.exception;

public class FileReaderException extends RuntimeException {

    public FileReaderException(String message, Throwable cause) throws FileReaderException {
        //super("No such file");
        try {
            throw new FileReaderException(cause);
        } catch (StackOverflowError e) {
            throw new FileReaderException(cause);
        }

    }

    public FileReaderException(Throwable t) {
        throw new FileReaderException("No such file", t);
    }

}
