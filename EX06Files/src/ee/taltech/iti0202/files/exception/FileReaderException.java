package ee.taltech.iti0202.files.exception;

public class FileReaderException extends RuntimeException {

    String msg;

    public FileReaderException(String msg, Throwable e) {
        this.msg = msg;
        System.out.println(msg);
    }

    @Override
    public String toString() {
        return msg;
    }
}
