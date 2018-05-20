package dev5.chermenin.rest.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * Created by Ancarian on 18.02.2018.
 */

public class InvalidTokenAuthenticationException extends AuthenticationException {

    public InvalidTokenAuthenticationException(final String msg, final Throwable throwable) {
        super(msg, throwable);
    }

    public InvalidTokenAuthenticationException(final String msg) {
        super(msg);
    }

}