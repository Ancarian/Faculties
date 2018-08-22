package dev.chermenin.dao;

import dev.chermenin.model.impl.University;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UniversityRepositoryTest {
    @Autowired
    private UniversityRepository universityRepository;

    @Test
    public void existsByEmailOrPhoneOrNameOrShortNameAndIdNot() {
        University university = universityRepository.findById(1L).get();
        assertFalse(universityRepository.existsByEmailOrPhoneOrNameOrShortNameAndIdNot(university.getEmail(),
                university.getPhone(), university.getName(), university.getShortName(), 1L));
        assertTrue(universityRepository.existsByEmailOrPhoneOrNameOrShortNameAndIdNot(university.getEmail(),
                university.getPhone(), university.getName(), university.getShortName(), 2L));

    }

    @Test
    public void existsByEmailOrPhoneOrNameOrShortName() {
        University university = universityRepository.findById(1L).get();
        assertTrue(universityRepository.existsByEmailOrPhoneOrNameOrShortName(university.getEmail(),
                university.getPhone(), university.getName(), university.getShortName()));
    }
}