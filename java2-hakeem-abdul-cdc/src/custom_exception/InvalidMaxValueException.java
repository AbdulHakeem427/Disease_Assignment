package custom_exception;

public class InvalidMaxValueException extends RuntimeException {
    public InvalidMaxValueException(String message) {
        super(message);
    }
}