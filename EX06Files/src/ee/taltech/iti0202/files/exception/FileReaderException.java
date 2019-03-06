package ee.taltech.iti0202.files.exception;

public class FileReaderException extends RuntimeException {

    public FileReaderException(Throwable cause) {
        throw new Exception(cause);
        //super("No such file");
    }

}
