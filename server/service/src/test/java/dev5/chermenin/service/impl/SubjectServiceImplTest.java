package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.SubjectRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.SubjectService;

import dev5.chermenin.service.dto.impl.subject.SubjectDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ancarian on 11.12.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
public class SubjectServiceImplTest {
    private final static Pageable pageable = new PageRequest(0, 100);

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;


    @Test
    public void findByExistId() throws Exception {
        SubjectDto subjectDto = subjectService.findById(2L);
        assertEquals(subjectDto.getSubject(), "math");
        assertEquals(subjectDto.getId(), (Long) 2L);
    }

    @Test
    public void findByName() throws Exception {
        SubjectDto subjectDto = subjectService.findByName("math");
        assertEquals(subjectDto.getSubject(), "math");
        assertEquals(subjectDto.getId(), (Long) 2L);
    }

    @Test
    public void saveSubjectDto() throws Exception {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubject("engl");
        subjectDto = subjectService.save(subjectDto);

        assertEquals(subjectDto.getId(), subjectService.findByName("engl").getId());
        subjectService.remove(subjectDto.getId());
    }

    @Test
    public void findAll() {
        assertEquals(subjectService.findAll(pageable).size(), 3);
    }

    @Transactional
    @Test
    public void addSubjectToUser() {
        subjectService.addSubjectToUser(13, 1, 100);
        assertTrue(userRepository.findOne(13L).getSubjects().containsKey(subjectRepository.findOne(1L)));
    }

    @Transactional
    @Test
    public void removeUserSubject() {
        subjectService.removeUserSubject(13, 3);
        assertTrue(!userRepository.findOne(13L).getSubjects().containsKey(subjectRepository.findOne(3L)));
    }

    @Test
    public void findSubjectsByInventoryIds() {
        List<Long> identificators = new ArrayList<>();
        identificators.add(1L);
        identificators.add(2L);
        identificators.add(1000L);

        List<SubjectDto> subjects = subjectService.findSubjectsByInventoryIds(identificators);

        assertEquals(subjects.size(), 2);
    }
}