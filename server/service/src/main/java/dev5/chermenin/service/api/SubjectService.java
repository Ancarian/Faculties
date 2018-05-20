package dev5.chermenin.service.api;

import dev5.chermenin.service.dto.impl.subject.SubjectDto;

import java.util.List;
import java.util.Set;

import java.util.Set;

public interface SubjectService extends GenericService<SubjectDto, Long> {
    SubjectDto findByName(String name);

    void addSubjectToUser(long id, long subject_id, int score);

    void removeUserSubject(long id, long subject_id);

    List<SubjectDto> findSubjectsByInventoryIds(List<Long> subjectIds);
}

