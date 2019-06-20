package ua.tracker.food.exception;

public class ApplicationCsrfErrorException extends ApplicationException {
    public ApplicationCsrfErrorException() {
    }

    public ApplicationCsrfErrorException(Throwable cause) {
        super(cause);
    }
}
