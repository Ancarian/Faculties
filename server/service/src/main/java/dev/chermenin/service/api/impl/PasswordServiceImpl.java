package dev.chermenin.service.api.impl;

import dev.chermenin.dao.PasswordTokenRepository;
import dev.chermenin.dao.UserRepository;
import dev.chermenin.model.impl.PasswordResetToken;
import dev.chermenin.model.impl.User;
import dev.chermenin.service.api.PasswordService;
import dev.chermenin.service.exception.ConflictException;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PasswordServiceImpl extends AbstractTokenService implements PasswordService {
    private static final int EXPIRATION = 60;
    private final UserRepository userRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public PasswordResetToken resetPassword(String email) {
        User user = userRepository.findByEmailAndEnabledTrue(email).orElseThrow(
                () -> new NotFoundException(String.format("user with email %s not exists or not enabled", email)));

        PasswordResetToken token = new PasswordResetToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(calculateExpiryDate());
        return passwordTokenRepository.save(token);
    }

    @Override
    public void resetPassword(Long id, String token, String password) {
        PasswordResetToken passwordResetToken = passwordTokenRepository.findByToken(token).orElseThrow(
                () -> new NotFoundException("token not exists"));

        if (!passwordResetToken.getUser().getId().equals(id) || isExpired(passwordResetToken.getExpiryDate())) {
            throw new ConflictException("incorrect token");
        }
        String newPassword = passwordEncoder.encode(password);
        passwordResetToken.getUser().setPassword(newPassword);
        passwordTokenRepository.save(passwordResetToken);
    }

    @Override
    protected int getExpiration() {
        return EXPIRATION;
    }
}
