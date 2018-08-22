package dev.chermenin.service.api.impl;

import dev.chermenin.dao.util.SpecsBuilder;
import dev.chermenin.model.impl.Subject;
import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.enums.Sex;
import dev.chermenin.service.api.UserService;
import dev.chermenin.dao.dto.create.UserRegistrationDto;
import dev.chermenin.dao.dto.subject.SubjectScoreDto;
import dev.chermenin.dao.dto.user.UserDto;
import dev.chermenin.dao.dto.user.UserProfileDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    private PageRequest pageable = PageRequest.of(0, 10);

    @Test
    public void findById() {
        Long id = 1L;
        UserProfileDto userProfileDto = userService.findById(id);

        Assert.assertNotNull(userProfileDto);
        Assert.assertEquals(id, userProfileDto.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNotExistsId() {
        Long id = 100L;
        userService.findById(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNotExistsEmail() {
        String email = "emailemailemailemail";
        userService.findByEmail(email);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByNotExistsNickname() {
        String nickname = "emailemailemailemail";
        userService.findByNickname(nickname);
    }

    @Test
    public void findByNickname() {
        String nickname = "nickname";
        UserProfileDto userProfileDto = userService.findByNickname(nickname);

        Assert.assertNotNull(userProfileDto);
        Assert.assertEquals(nickname, userProfileDto.getNickname());
    }

    @Test
    public void findByEmail() {
        String email = "email";
        UserProfileDto userProfileDto = userService.findByEmail(email);

        Assert.assertNotNull(userProfileDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveUserWithExistsCredentials() {
        UserRegistrationDto user = new UserRegistrationDto();
        user.setName("name");
        user.setLastname("lastname");
        user.setPatronymic("patronymic");
        user.setEmail("email");
        user.setPassword("password");
        user.setPhotoUrl("url");
        user.setSex(Sex.MALE);
        user.setNickname("nickname");
        user.setCityId(1L);
        user.setAdress("adress");

        SubjectScoreDto subjectScoreDto = new SubjectScoreDto();
        subjectScoreDto.setSubject(new Subject());
        subjectScoreDto.getSubject().setId(1L);
        subjectScoreDto.setScore(100);


        List<SubjectScoreDto> subjectScoreDtos = new ArrayList<>();
        subjectScoreDtos.add(subjectScoreDto);
        user.setSubjects(subjectScoreDtos);

        UserProfileDto userProfileDto = userService.save(user);

        Assert.assertNotNull(userProfileDto.getId());

        userService.remove(userProfileDto.getId());
    }

    @Test
    public void save() {
        UserRegistrationDto user = new UserRegistrationDto();
        user.setName("name");


        user.setLastname("lastname");
        user.setPatronymic("patronymic");
        user.setEmail("email124");
        user.setPassword("password");
        user.setPhotoUrl("url");
        user.setSex(Sex.MALE);
        user.setNickname("nick124");
        user.setCityId(1L);
        user.setAdress("adress");

        SubjectScoreDto subjectScoreDto = new SubjectScoreDto();
        subjectScoreDto.setSubject(new Subject());
        subjectScoreDto.getSubject().setId(1L);
        subjectScoreDto.setScore(100);

        List<SubjectScoreDto> subjectScoreDtos = new ArrayList<>();
        subjectScoreDtos.add(subjectScoreDto);
        user.setSubjects(subjectScoreDtos);

        UserProfileDto userProfileDto = userService.save(user);
        Assert.assertNotNull(userProfileDto.getId());
        userService.remove(userProfileDto.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteWithNotExistUser() {
        userService.remove(100L);
    }

    @Test
    public void findAll() {
        Assert.assertTrue(userService.findAll(pageable).getContent().size() != 0);
    }

    @Test
    public void findAllWithSpecs() {
        String search = "name:name";
        Specification<User> spec = new SpecsBuilder<User>().createSpecification(search);
        userService.findAll(pageable, spec).getContent().forEach(user ->
                Assert.assertTrue(user.getName().contains("name"))
        );
    }

    @Test
    public void getRequests() {
        Long groupId = 1L;
        List<UserDto> users = userService.getRequests(groupId, pageable).getContent();

        users.forEach(user -> {
            UserProfileDto profileDto = userService.findById(user.getId());
            Assert.assertEquals(groupId, profileDto.getGroup().getId());
            Assert.assertFalse(profileDto.isVerified());
        });
    }
}