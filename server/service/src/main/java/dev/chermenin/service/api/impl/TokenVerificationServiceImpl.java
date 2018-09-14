package dev.chermenin.service.api.impl;

import dev.chermenin.dao.TokenVerificationRepository;
import dev.chermenin.dao.UserRepository;
import dev.chermenin.model.impl.EmailVerificationToken;
import dev.chermenin.model.impl.User;
import dev.chermenin.service.api.TokenVerificationService;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenVerificationServiceImpl implements TokenVerificationService {
    private static final int EXPIRATION = 60 * 24;
    private final TokenVerificationRepository tokenVerificationRepository;
    private final UserRepository userRepository;

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
    public boolean isExpired(EmailVerificationToken token) {
        Calendar cal = Calendar.getInstance();
        return (token.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0;
    }

    @Override
    public EmailVerificationToken generateNewVerificationToken(String token) {
        EmailVerificationToken newToken = findByToken(token);

        newToken.setToken(UUID.randomUUID().toString());
        newToken.setExpiryDate(calculateExpiryDate());
        return tokenVerificationRepository.save(newToken);
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

}
