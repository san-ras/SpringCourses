package lt.techin.springRestSR.exceptions;

public class StudentCanNotEnrollException extends RuntimeException {
    public StudentCanNotEnrollException(String s) {
        super(s);
    }
}
