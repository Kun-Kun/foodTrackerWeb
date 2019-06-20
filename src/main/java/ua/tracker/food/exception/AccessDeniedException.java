package ua.tracker.food.exception;

public class AccessDeniedException  extends ApplicationException{
    public AccessDeniedException() {
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
