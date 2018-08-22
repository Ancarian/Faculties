package dev.chermenin.service.api;


import dev.chermenin.dao.dto.subject.SubjectDto;
import dev.chermenin.dao.dto.subject.SubjectScoreDto;

import java.util.List;

public interface SubjectService {
    void updateUserSubjects(List<SubjectScoreDto> subjectScoreDtoList, Long userId);

    void save(List<SubjectDto> subjects);

    void save(SubjectDto subjects);

    List<SubjectDto> findAll();
}

