package dev.chermenin.service.api.impl;

import dev.chermenin.dao.SubjectRepository;
import dev.chermenin.dao.dto.subject.SubjectScoreDto;
import dev.chermenin.model.impl.Subject;
import dev.chermenin.service.api.SubjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class SubjectServiceImplTest {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    public void updateUserSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        List<SubjectScoreDto> subjectScoreDtoList = new ArrayList<>();

        subjects.forEach(subject -> {
            SubjectScoreDto subjectScoreDto = new SubjectScoreDto();
            subjectScoreDto.setSubject(subject);
            subjectScoreDto.setScore(50);
            subjectScoreDtoList.add(subjectScoreDto);
        });

        subjectService.updateUserSubjects(subjectScoreDtoList, 1L);
    }

    @Test
    public void save() {
    }

    @Test
    public void save1() {
    }

    @Test
    public void findAll() {
    }
}