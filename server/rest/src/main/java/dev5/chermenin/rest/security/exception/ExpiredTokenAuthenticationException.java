package dev5.chermenin.rest.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * Created by Ancarian on 18.02.2018.
 */

public class ExpiredTokenAuthenticationException extends AuthenticationException {

    public ExpiredTokenAuthenticationException() {
        super("Authentication token is expired.");
    }
}