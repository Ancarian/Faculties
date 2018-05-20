package dev5.chermenin.service.api;

import dev5.chermenin.service.dto.impl.user.UserDto;

import java.util.List;

public interface EmailService {

    void sendMessage(String to, String subject, String text);

    void sendEmailToUsers(List<UserDto> users, String subject, String text);


    void send();

}
