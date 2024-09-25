package custom_exception;

public class  UnknowExposureTypeException extends RuntimeException  {
    public UnknowExposureTypeException (String message) {
        super(message);
    }
}