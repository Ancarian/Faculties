package dev.chermenin.service.api.impl;

import dev.chermenin.dao.dto.user.UserDto;
import dev.chermenin.model.impl.EmailVerificationToken;
import dev.chermenin.model.impl.PasswordResetToken;
import dev.chermenin.service.api.EmailService;
import dev.chermenin.service.api.model.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final SimpleMailMessage mailMessage;
    private final JavaMailSender emailSender;
    private final MessageSource messages;
    private final TemplateEngine templateEngine;

    @Async
    public void sendMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @Async
    public void sendEmailToUsers(List<UserDto> users, String subject, String text) {
//        mailMessage.setTo(users.stream().map(user -> user.getEmail()).toArray(String[]::new));
//        mailMessage.setSubject(subject);
//        mailMessage.setText(text);
//        emailSender.send(mailMessage);
    }

    @Async
    @Scheduled(cron = "*/10 * * * * *")
    public void send() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    @Async
    @Override
    public void confirmRegistration(EmailVerificationToken token, Locale locale) throws Exception {
        String recipientAddress = token.getUser().getEmail();
        String subject = messages.getMessage("email.EmailConfirm", null, locale);
        String buttonSubmit = messages.getMessage("email.ButtonSubmit", null, locale);
        String orLink = messages.getMessage("email.OrLink", null, locale);

        String confirmationUrl = "http://localhost:8080/auth/registrationConfirm?token=" + token.getToken();

        Mail mail = new Mail();
        mail.setTo(recipientAddress);
        mail.setSubject(subject);
        mail.setFrom("facultiesgit@gmail.com");
        mail.setTemplate("EmailConfirm");
        Map<String, Object> model = new HashMap<>();
        model.put("message", confirmationUrl);
        model.put("buttonSubmit", buttonSubmit);
        model.put("orLink", orLink);
        model.put("messageType", subject);
        mail.setModel(model);

        sendSimpleMessage(mail);
    }

    @Async
    @Override
    public void resetPassword(PasswordResetToken token, Locale locale) throws Exception {
        String recipientAddress = token.getUser().getEmail();
        String subject = messages.getMessage("email.ResetPassword", null, locale);
        String buttonSubmit = messages.getMessage("email.ButtonSubmit", null, locale);

        String confirmationUrl = "http://localhost:8080/auth/changePassword?token=" + token.getToken() + "&id=" + token.getUser().getId();

        Mail mail = new Mail();
        mail.setTo(recipientAddress);
        mail.setSubject(subject);
        mail.setFrom("facultiesgit@gmail.com");
        mail.setTemplate("EmailConfirm");
        Map<String, Object> model = new HashMap<>();
        model.put("message", confirmationUrl);
        model.put("buttonSubmit", buttonSubmit);
        model.put("messageType", subject);
        mail.setModel(model);

        sendSimpleMessage(mail);
    }

    private void sendSimpleMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        Context context = new Context();
        context.setVariables(mail.getModel());
        String html = templateEngine.process(mail.getTemplate(), context);

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }
}

