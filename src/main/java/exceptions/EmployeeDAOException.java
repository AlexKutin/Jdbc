package exceptions;

public class EmployeeDAOException extends RuntimeException {

    public EmployeeDAOException(String message) {
        super(message);
    }
}
