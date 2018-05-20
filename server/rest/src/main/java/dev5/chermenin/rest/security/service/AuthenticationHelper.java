package dev5.chermenin.rest.security.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev5.chermenin.rest.security.exception.InvalidTokenAuthenticationException;
import dev5.chermenin.rest.security.model.JwtUserDetails;
import dev5.chermenin.rest.security.model.TokenPayload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Ancarian on 18.02.2018.
 */
@Component
public class AuthenticationHelper {

    public static final String AUTHENTICATION_HEADER = "Authorization";
    public static final String AUTHENTICATION_PARAM = "auth";
    private final ObjectMapper objectMapper;
    @Value("${jwt.token.secret}")
    private String SECRET;
    private Long tokenExpirationTime = 3600L;

    public AuthenticationHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String generateToken(final Long userId) {
        try {
            TokenPayload payload = new TokenPayload(
                    userId, Instant.now().getEpochSecond() + this.tokenExpirationTime);
            String token = this.objectMapper.writeValueAsString(payload);
            return JwtHelper.encode(token, new MacSigner(SECRET)).getEncoded();
        } catch (JsonProcessingException exception) {
            throw new InternalAuthenticationServiceException("Error generating token.", exception);
        }
    }

    public String generateToken(final JwtUserDetails userDetails) {

        Set<String> roles = new HashSet<>();
        userDetails.getAuthorities().forEach(grantedAuthority -> roles.add(grantedAuthority.toString()));

        try {
            TokenPayload payload = new TokenPayload(userDetails.getId(),
                    Instant.now().getEpochSecond() + this.tokenExpirationTime,
                    roles);

            String token = this.objectMapper.writeValueAsString(payload);
            return JwtHelper.encode(token, new MacSigner(SECRET)).getEncoded();
        } catch (JsonProcessingException exception) {
            throw new InternalAuthenticationServiceException("Error generating token.", exception);
        }
    }

    public TokenPayload decodeToken(final String token) {
        if (Objects.isNull(token)) {
            throw new InvalidTokenAuthenticationException("Token was null or blank.");
        }
        // Getting JWT object from string token
        Jwt jwt = JwtHelper.decode(token);

        // Validate token signature (to be sure that token has not been tampered with)
        try {
            jwt.verifySignature(new MacSigner(SECRET));
        } catch (Exception exception) {
            throw new InvalidTokenAuthenticationException("Token signature verification failed.", exception);
        }

        // Getting payload of token
        String claims = jwt.getClaims();
        try {
            return this.objectMapper.readValue(claims, TokenPayload.class);
        } catch (IOException exception) {
            throw new InvalidTokenAuthenticationException("Token parsing failed.", exception);
        }

    }
}