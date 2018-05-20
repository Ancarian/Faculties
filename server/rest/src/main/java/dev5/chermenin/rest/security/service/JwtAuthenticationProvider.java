package dev5.chermenin.rest.security.service;

import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.rest.security.exception.ExpiredTokenAuthenticationException;
import dev5.chermenin.rest.security.exception.InvalidTokenAuthenticationException;
import dev5.chermenin.rest.security.model.JwtAuthenticationToken;
import dev5.chermenin.rest.security.model.JwtUserDetails;
import dev5.chermenin.rest.security.model.TokenPayload;
import dev5.chermenin.service.api.UserInformationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by Ancarian on 18.02.2018.
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private static final long MILLIS_IN_SECOND = 1000L;

    private final UserInformationService userInformationService;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public JwtAuthenticationProvider(UserInformationService userInformationService, AuthenticationHelper authenticationHelper) {
        this.userInformationService = userInformationService;
        this.authenticationHelper = authenticationHelper;
    }

    @Override
    public Authentication authenticate(final Authentication authRequest) {
        // Getting string token from authentication request object
        String token = StringUtils.trimToNull((String) authRequest.getCredentials());

        //  Deserialize token
        TokenPayload tokenPayload = authenticationHelper.decodeToken(token);

        // Checking if token already expired and throwing an AuthenticationException in this case
        checkIsExpired(tokenPayload.getExp());

        // Getting user id from token
        Long userEntityId = tokenPayload.getUserId();
        if (Objects.isNull(userEntityId)) {
            throw new InvalidTokenAuthenticationException("Token does not contain a user id.");
        }

        // Getting user from database
        UserInformation userInformation = userInformationService.findUserInfoById(userEntityId);

        if (Objects.isNull(userInformation)) {
            throw new InvalidTokenAuthenticationException("Token does not contain existed user id.");
        }


        // Return authenticated Authentication
        JwtUserDetails userDetails = new JwtUserDetails(userInformation);
        return new JwtAuthenticationToken(userDetails);
    }

    private void checkIsExpired(final Long tokenExpirationTime) {
        if ((System.currentTimeMillis() / MILLIS_IN_SECOND) > tokenExpirationTime) {
            throw new ExpiredTokenAuthenticationException();
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}