package ua.tracker.food.exception;

public class InvalidInputException extends ApplicationException {
    public InvalidInputException() {
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
