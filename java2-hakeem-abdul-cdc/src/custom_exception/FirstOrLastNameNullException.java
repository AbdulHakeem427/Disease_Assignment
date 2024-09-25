package custom_exception;

public class FirstOrLastNameNullException extends RuntimeException {
    public FirstOrLastNameNullException(String message) {
        super(message);
    }
}
