package dev.chermenin.service.api;


import dev.chermenin.model.impl.University;
import dev.chermenin.dao.dto.create.UniversityRegistrationDto;
import dev.chermenin.dao.dto.university.UniversityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UniversityService {

    UniversityDto save(UniversityRegistrationDto universityRegistrationDto);

    void update(UniversityRegistrationDto universityRegistrationDto);

    UniversityDto findById(Long id);

    Page<UniversityDto> findAll(Pageable pageable);

    Page<UniversityDto> findAll(Pageable pageable, Specification<UniversityDto> specification);

    void remove(Long id);
}

