package dev.chermenin.dao;

import dev.chermenin.model.impl.Subject;
import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.adress.City;
import dev.chermenin.model.impl.enums.Roles;
import dev.chermenin.model.impl.enums.Sex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UserInformationRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        if (userRepository.count() == 0) {
            Assert.fail("database is empty");
        }
    }

    @Test
    public void saveUserTest(){
        User user = new User();
        user.setAdress("adress");
        user.setEmail("email@gmail.com");
        user.setLastname("lastname");
        user.setPatronymic("patr");
        user.setPassword("password");
        user.setNickname("nick");
        user.setSex(Sex.MALE);
        user.setRole(Roles.USER);
        user.setCity(new City());
        user.getCity().setId(1L);
        user.setName("name");
        Map<Subject, Integer> subjects = new HashMap<>();
        Subject subject1 = new Subject();
        subject1.setId(1L);
        subjects.put(subject1, 100);
        Subject subject2 = new Subject();
        subject2.setId(2L);
        subjects.put(subject2, 200);

        user.setSubjects(subjects);
        user = userRepository.save(user);

        Assert.assertNotNull(user.getId());

        userRepository.delete(user);

    }
}