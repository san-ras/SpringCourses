package lt.techin.springRestSR.exceptions;

public class InvalidCourseDetailsException extends RuntimeException {
    public InvalidCourseDetailsException(String message) {
        super(message);
    }
}
