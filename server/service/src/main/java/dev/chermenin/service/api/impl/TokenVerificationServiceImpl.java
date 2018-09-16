package dev.chermenin.service.api.impl;

import dev.chermenin.dao.TokenVerificationRepository;
import dev.chermenin.dao.UserRepository;
import dev.chermenin.model.impl.EmailVerificationToken;
import dev.chermenin.model.impl.User;
import dev.chermenin.service.api.TokenVerificationService;
import dev.chermenin.service.api.UserUpdateService;
import dev.chermenin.service.exception.BadRequestException;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenVerificationServiceImpl extends AbstractTokenService implements TokenVerificationService {
    private static final int EXPIRATION = 60 * 24;
    private final TokenVerificationRepository tokenVerificationRepository;
    private final UserRepository userRepository;
    private final UserUpdateService userUpdateService;

    @Override
    public EmailVerificationToken createVerificationToken(User user) {
        EmailVerificationToken token = new EmailVerificationToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(calculateExpiryDate());
        return tokenVerificationRepository.save(token);
    }

    @Override
    public EmailVerificationToken findByToken(String token) {
        return tokenVerificationRepository.findByToken(token).
                orElseThrow(() -> new NotFoundException("error.emailToken.NotExists"));
    }

    @Override
    public User findUserByToken(String verificationToken) {
        return userRepository.findUserByToken(verificationToken)
                .orElseThrow(() -> new NotFoundException("error.user.NotExistsId"));
    }

    @Override
    public void enableUser(String token) {
        EmailVerificationToken verificationToken = findByToken(token);
        User user = verificationToken.getUser();

        if (isExpired(verificationToken.getExpiryDate()) || user.isEnabled()) {
            throw new BadRequestException("token has been expired");
        }
        userUpdateService.enableUser(user);
    }

    @Override
    public EmailVerificationToken generateNewVerificationToken(String token) {
        EmailVerificationToken newToken = findByToken(token);

        newToken.setToken(UUID.randomUUID().toString());
        newToken.setExpiryDate(calculateExpiryDate());
        return tokenVerificationRepository.save(newToken);
    }

    @Override
    protected int getExpiration() {
        return EXPIRATION;
    }
}
