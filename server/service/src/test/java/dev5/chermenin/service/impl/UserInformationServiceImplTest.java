package dev5.chermenin.service.impl;

import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.UserInformationService;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.credentials.CredentialEmailDto;
import dev5.chermenin.service.dto.impl.credentials.CredentialPasswordDto;
import dev5.chermenin.service.exceptions.ConflictException;
import dev5.chermenin.service.exceptions.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ancarian on 22.10.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
public class UserInformationServiceImplTest {
    @Autowired
    private UserInformationService infoService;

    @Test
    public void findById() throws Exception {
        assertEquals(infoService.findById(1L).getId(), new Long(1));
    }

    @Test
    public void findByNickname() throws Exception {
        assertEquals(infoService.findByNickname("nickname_1").getNickname(), "nickname_1");
    }

    @Test(expected = NotFoundException.class)
    public void findByNonexistentNickname() throws Exception {
        infoService.findByNickname("NotExistNick");
    }

    @Test
    public void findByEmail() throws Exception {
        assertEquals(infoService.findByEmail("email_1").getEmail(), "email_1");
    }

    @Test
    public void changeEmailUserInformation() {
        infoService.changeEmail(new CredentialEmailDto(2L, "oldEmail@gmail.com", "newEmail@gmail.com"));
        assertEquals(infoService.findById(2L).getEmail(), "newEmail@gmail.com");
    }

    @Test(expected = NullPointerException.class)
    public void changePasswordNotExistUserInformation() {
        infoService.changePassword(new CredentialPasswordDto(1000L, "oldPassword", "newPassword"));
    }


    @Test(expected = ConflictException.class)
    public void changePasswordUserInformation() {
        infoService.changePassword(new CredentialPasswordDto(2L, "invalidOldPassword", "newPassword"));
        assertEquals(infoService.findById(2L).getPassword(), "newPassword");
    }

}