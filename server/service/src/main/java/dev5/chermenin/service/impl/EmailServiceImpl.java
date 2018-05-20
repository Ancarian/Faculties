package dev5.chermenin.service.impl;

import dev5.chermenin.service.api.EmailService;
import dev5.chermenin.service.dto.impl.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final SimpleMailMessage mailMessage;
    private final JavaMailSender emailSender;

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
        mailMessage.setTo(users.stream().map(user -> user.getUserInformationDto().getEmail()).toArray(String[]::new));
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        emailSender.send(mailMessage);
    }

    @Async
    @Scheduled(cron = "*/10 * * * * *")
    public void send() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
}

