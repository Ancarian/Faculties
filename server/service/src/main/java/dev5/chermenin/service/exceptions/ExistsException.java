package dev5.chermenin.service.exceptions;

public class ExistsException extends RuntimeException {
    public ExistsException() {
    }

    public ExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistsException(String message) {
        super(message);
    }

    public ExistsException(Throwable cause) {
        super(cause);
    }
}

