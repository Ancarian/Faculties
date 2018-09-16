package dev.chermenin.service.api;


import dev.chermenin.dao.dto.user.UserDto;
import dev.chermenin.model.impl.EmailVerificationToken;
import dev.chermenin.model.impl.PasswordResetToken;

import java.util.List;
import java.util.Locale;

public interface EmailService {

    void sendMessage(String to, String subject, String text);

    void sendEmailToUsers(List<UserDto> users, String subject, String text);

    void send();

    void confirmRegistration(EmailVerificationToken token, Locale locale) throws Exception;

    void resetPassword(PasswordResetToken token, Locale locale) throws Exception;

}
