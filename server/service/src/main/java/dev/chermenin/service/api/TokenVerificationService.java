package dev.chermenin.service.api;

import dev.chermenin.model.impl.EmailVerificationToken;
import dev.chermenin.model.impl.User;

public interface TokenVerificationService {
    EmailVerificationToken createVerificationToken(User user);

    EmailVerificationToken findByToken(String token);

    User findUserByToken(String verificationToken);

    boolean isExpired(EmailVerificationToken token);
}
