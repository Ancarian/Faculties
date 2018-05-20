package dev5.chermenin.rest.security.service;

import dev5.chermenin.rest.security.handler.RestAuthenticationFailureHandler;
import dev5.chermenin.rest.security.model.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by Ancarian on 18.02.2018.
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationFilter(final AuthenticationManager authenticationManager) {
        super(request -> true);
        setAuthenticationManager(authenticationManager);
        setAuthenticationFailureHandler(new RestAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
            throws IOException, ServletException {
        try {
            // Getting JWT token from request
            String token = Optional.ofNullable(request.getHeader(AuthenticationHelper.AUTHENTICATION_HEADER))
                    .map(header -> header.substring(7)).orElse(null);

            if (Objects.isNull(token)) {
                throw new BadCredentialsException("Token not found in request's header.");
            }

            // Create token for authentication provider
            JwtAuthenticationToken authRequest = new JwtAuthenticationToken(token);

            // Return a fully authenticated object
            return this.getAuthenticationManager().authenticate(authRequest);
        } catch (AuthenticationException exception) {
            // Go to 401 error page if exception thrown
            unsuccessfulAuthentication(request, response, exception);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
                                            final FilterChain chain, final Authentication authResult)
            throws IOException, ServletException {
        // Set authentication to context
        SecurityContextHolder.getContext().setAuthentication(authResult);

        // Fire event
        if (this.eventPublisher != null) {
            this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }

        chain.doFilter(request, response);
    }
}