package dev.chermenin.service.api.impl;

import dev.chermenin.model.impl.enums.Sex;
import dev.chermenin.service.api.UserService;
import dev.chermenin.service.api.UserUpdateService;
import dev.chermenin.dao.dto.create.UserRegistrationDto;
import dev.chermenin.dao.dto.user.UserProfileDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UserUpdateServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserUpdateService userUpdateService;
    private PageRequest pageable = PageRequest.of(0, 10);

    @Test
    public void update() {
        UserProfileDto userProfileDto = userService.findById(1L);
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setId(1L);
        userRegistrationDto.setSubjects(userProfileDto.getSubjects());
        userRegistrationDto.setAdress(userProfileDto.getAdress());
        userRegistrationDto.setName("newName");
        userRegistrationDto.setLastname("newLastname");
        userRegistrationDto.setPatronymic("newPatronymic");
        userRegistrationDto.setPhotoUrl("url");
        userRegistrationDto.setSex(Sex.MALE);
        userRegistrationDto.setCityId(1L);

        userUpdateService.update(userRegistrationDto);
        userProfileDto = userService.findById(1L);
        Assert.assertEquals(userProfileDto.getName(), "newName");
        Assert.assertEquals(userProfileDto.getLastname(), "newLastname");
        Assert.assertEquals(userProfileDto.getPatronymic(), "newPatronymic");
        Assert.assertEquals(userProfileDto.getSex(), Sex.MALE);
        //Assert.assertEquals(userProfileDto.getCity().getId(), (Long) 1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithNullId() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userUpdateService.update(userRegistrationDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateWithIncorrectId() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setId(1000L);
        userUpdateService.update(userRegistrationDto);
    }

    @Test
    public void selectGroup() {
    }

    @Test
    public void changeStateOfRequest() {
    }

    @Test
    public void setPhotoToUser() {
    }

    @Test
    public void changePassword() {
    }

    @Test
    public void changeEmail() {
    }
}