package dev.chermenin.dao;

import dev.chermenin.dao.dto.faculty.FacultyDto;
import dev.chermenin.model.impl.Faculty;
import dev.chermenin.model.impl.University;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class FacultyRepositoryTest {
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private UniversityRepository universityRepository;
    private final PageRequest pageRequest = PageRequest.of(0, 10);

    @Transactional
    @Test
    public void findAllByUniversityId() {
        Page<Faculty> faculties = facultyRepository.findAllByUniversityId(1L, pageRequest);
    }
}