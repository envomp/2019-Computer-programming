package ee.taltech.iti0202.files.exception;

public class FileReaderException extends RuntimeException {

    public FileReaderException(Throwable cause) throws RuntimeException {
        //super("No such file");
        throw new RuntimeException("No such file", cause);
    }

}
