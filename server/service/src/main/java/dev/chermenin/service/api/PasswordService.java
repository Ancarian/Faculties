package dev.chermenin.service.api;

import dev.chermenin.model.impl.PasswordResetToken;

import java.util.Date;

public interface PasswordService {
    PasswordResetToken resetPassword(String email);

    void resetPassword(Long id, String token, String password);
}
