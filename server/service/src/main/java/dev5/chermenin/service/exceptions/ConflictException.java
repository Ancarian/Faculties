package dev5.chermenin.service.exceptions;

public class ConflictException extends RuntimeException {
    public ConflictException() {
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }
}

