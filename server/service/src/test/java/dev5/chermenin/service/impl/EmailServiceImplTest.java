package dev5.chermenin.service.impl;

import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.dto.impl.user.UserInformationDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
@ComponentScan(basePackages = {"dev5.chermenin"})
public class EmailServiceImplTest {
    private final static Pageable pageable = new PageRequest(0, 100);
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void sendSimpleMessage() {
        emailServiceImpl.sendMessage("peeweehamstergsgdfasg@gmail.com", "test", "testing");
    }

    @Test
    public void sendMessageToUsers() {
        List<UserDto> userDtos = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            userDtos.add(new UserDto());
            userDtos.get(i).setUserInformationDto(new UserInformationDto());
            userDtos.get(i).getUserInformationDto().setEmail(i + "@gmail.com");
        }

        userDtos.add(new UserDto());
        userDtos.get(10).setUserInformationDto(new UserInformationDto());
        userDtos.get(10).getUserInformationDto().setEmail("peeweehamster@gmail.com");
        emailServiceImpl.sendEmailToUsers(userDtos, "test", "testing");
    }
}