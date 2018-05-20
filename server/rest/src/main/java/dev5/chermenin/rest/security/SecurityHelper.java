package dev5.chermenin.rest.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {

    public static Authentication getAuthenticationWithCheck() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean checkAuthenticationExists = authentication != null && authentication.isAuthenticated();
        if (checkAuthenticationExists) {
            return authentication;
        }

        throw new RuntimeException("Authentication failed.");
    }
}