package dev.chermenin.service.api;


import dev.chermenin.model.impl.Faculty;
import dev.chermenin.dao.dto.create.FacultyRegistrationDto;
import dev.chermenin.dao.dto.faculty.FacultyDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface FacultyService {
    FacultyDto findById(Long id);

    Page<FacultyDto> findAllByUniversityId(Pageable pageable, Long id);

    Page<FacultyDto> findAll(Pageable pageable);

    Page<FacultyDto> findAll(Pageable pageable, Specification<Faculty> specification);

    void remove(Long id);

    FacultyDto save(FacultyRegistrationDto id);

    void update(FacultyRegistrationDto id);
}

