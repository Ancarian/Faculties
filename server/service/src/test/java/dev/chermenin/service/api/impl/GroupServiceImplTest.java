package dev.chermenin.service.api.impl;

import dev.chermenin.service.api.GroupService;
import dev.chermenin.service.api.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class GroupServiceImplTest {
    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @Test
    public void findById() {
        assertEquals((Long)1L, groupService.findById(1L).getId());
    }

    @Test
    public void findAllByFacultyId() {
    }



    @Test
    public void findAll() {
    }

    @Test
    public void addToWishList() {
        groupService.addToWishList(1L, 1L);

    }

    @Test
    public void findAll1() {
    }

    @Test
    public void findById1() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void save() {
    }

    @Test
    public void update() {
    }
}