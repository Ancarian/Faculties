package dev.chermenin.rest.api;

import dev.chermenin.service.exception.ConflictException;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@AllArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {
        GenericResponse bodyOfResponse = new GenericResponse(messageSource.getMessage(ex.getMessage(), null, request.getLocale()), "UserNotFound");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        GenericResponse bodyOfResponse = new GenericResponse(messageSource.getMessage(ex.getMessage(), null, request.getLocale()), "409");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal(RuntimeException ex, WebRequest request) {
        GenericResponse bodyOfResponse = new GenericResponse(ex.getMessage(), "InternalError");
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Getter
    @Setter
    public static class GenericResponse {
        private String message;
        private String error;

        public GenericResponse(String message) {
            super();
            this.message = message;
        }

        public GenericResponse(String message, String error) {
            super();
            this.message = message;
            this.error = error;
        }
    }
}