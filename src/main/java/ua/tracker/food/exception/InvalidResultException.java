package ua.tracker.food.exception;

public class InvalidResultException extends ApplicationException {
    public InvalidResultException(Throwable cause) {
        super(cause);
    }

    public InvalidResultException() {
    }

    public InvalidResultException(String message) {
        super(message);
    }
}
