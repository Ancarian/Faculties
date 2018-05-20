package dev5.chermenin.service.api;

import dev5.chermenin.model.entity.impl.University;
import dev5.chermenin.service.dto.impl.UniversityDto;

public interface UniversityService extends GenericService<UniversityDto, Long> {

    void addFacultyToUniversity(Long facultyId, Long universityId);
    void removeFacultyFromUniversity(Long facultyId, Long universityId);
}

