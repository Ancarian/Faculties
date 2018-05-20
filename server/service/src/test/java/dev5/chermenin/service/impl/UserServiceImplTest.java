package dev5.chermenin.service.impl;

import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.RegisterDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ancarian on 22.10.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
public class UserServiceImplTest {
    private final static Pageable pageable = new PageRequest(0, 100);
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @Test
    public void findById() throws Exception {
        assertEquals((Long) 1L, userService.findById(1L).getId());
    }

    @Test
    public void save() throws Exception {
        RegisterDto user = new RegisterDto();

        user.setLastname("lastname");
        user.setPatronymic("patronymic");

        user.setName("username_" + 18);
        user.setEmail("email_" + 18);
        user.setPassword("password_" + 18);
        user.setNickname("nickname_" + 18);
        user = userService.save(user);

        userService.remove(user.getId());
    }

    @Test
    public void update() {
        ProfileUserDto profileUserDto = userService.findProfileById(1);
        profileUserDto.setName("Ancarian");
        userService.update(profileUserDto);
    }

    @Ignore
    @Test
    public void remove() {
        userService.remove(1L);
    }

    @Test
    public void findAll() throws Exception {
        assertEquals(userService.findAll(pageable).size(), 17);
    }

    @Test
    public void getRequests() throws Exception {
        userService.selectGroup(15, 2);
        List<UserDto> users = userService.getRequests();
        assertEquals(users.size(), 3);
    }

    @Test
    public void changeStateOfRequestFalse() throws Exception {
        UserDto userDto = userService.findById(15);

        userService.selectGroup(userDto.getId(), 1);
        userService.changeStateOfRequest(userDto.getId(), false);

        userDto = userService.findById(15);
        assertNull(userDto.getGroupId());
        assertFalse(userService.getRequests().contains(userDto));
    }

    @Test
    public void changeStateOfRequestTrue() throws Exception {
        userService.selectGroup(15, 2);
        userService.changeStateOfRequest(15, true);
        UserDto userDto = userService.findById(15);

        Long answer = 2L;
        assertEquals(userDto.getGroupId(), answer);
        assertFalse(userService.getRequests().contains(userDto));

    }

    @Test
    public void selectGroup() throws Exception {
        userService.selectGroup(15, 1);
        assertEquals(userService.findById(15).getGroupId(), (Long) 1L);
    }

}