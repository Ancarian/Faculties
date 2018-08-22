package dev.chermenin.service.api.impl;

import dev.chermenin.service.api.UniversityService;
import dev.chermenin.service.api.UserService;
import dev.chermenin.dao.dto.create.UniversityRegistrationDto;
import dev.chermenin.dao.dto.university.UniversityDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UniversityServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UniversityService universityService;
    private PageRequest pageable = PageRequest.of(0, 10);

    @Test
    public void save() {
        UniversityRegistrationDto university = createUniversity();
        UniversityDto universityDto = universityService.save(university);
        assertNotNull(universityDto.getId());
        universityService.remove(universityDto.getId());
    }

    @Test
    public void saveWithExistsData() {
        UniversityRegistrationDto university = createUniversity();
        UniversityDto universityDto = universityService.save(university);
        try {
            universityService.save(createUniversity());
            Assert.fail();
        } catch (IllegalArgumentException e) {
            universityService.remove(universityDto.getId());
        }
    }

    @Test
    public void updateWithExistsData() {
        UniversityRegistrationDto university = createUniversity();
        UniversityDto universityDto = universityService.save(university);
        UniversityRegistrationDto university2 = createUniversity();
        university2.setPhone("416257");
        university2.setEmail("email22");
        university2.setShortName("sName22");
        university2.setName("BNTU2");
        UniversityDto universityDto2 = universityService.save(university2);
        try {
            university2.setName("BNTU");
            university2.setEmail("email2");
            university2.setShortName("sName22");
            university2.setId(universityDto2.getId());
            universityService.update(university2);
            Assert.fail();
        } catch (IllegalArgumentException e) {
            universityService.remove(universityDto.getId());
        }
    }

    @Test
    public void update() {
        UniversityRegistrationDto university = createUniversity();
        UniversityDto universityDto = universityService.save(university);
        university.setEmail("newEmail");
        university.setId(universityDto.getId());
        universityService.update(university);
        universityDto = universityService.findById(university.getId());
        assertEquals(universityDto.getEmail(), "newEmail");
        universityService.remove(universityDto.getId());
    }

    @Test
    public void findById() {
        assertEquals((Long) 1L, universityService.findById(1L).getId());
    }

    @Test
    public void findAll() {
        assertNotNull(universityService.findAll(pageable).getContent());
    }

    private UniversityRegistrationDto createUniversity() {
        UniversityRegistrationDto university = new UniversityRegistrationDto();
        university.setName("BNTU");
        university.setShortName("BNTU");
        university.setInformation("info");
        university.setEmail("email");
        university.setPhone("12432346");
        university.setAdress("sfdhsdfh");
        university.setCityId(1L);
        return university;
    }
}