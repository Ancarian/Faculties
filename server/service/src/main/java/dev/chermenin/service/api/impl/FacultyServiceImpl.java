package dev.chermenin.service.api.impl;

import dev.chermenin.dao.FacultyRepository;
import dev.chermenin.dao.dto.create.FacultyRegistrationDto;
import dev.chermenin.dao.dto.faculty.FacultyDto;
import dev.chermenin.model.impl.Faculty;
import dev.chermenin.model.impl.University;
import dev.chermenin.service.api.FacultyService;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    @Override
    public FacultyDto findById(Long id) {
        return new FacultyDto(facultyRepository.findById(id).orElseThrow(
                () -> new NotFoundException("error.faculty.notExistsId")));
    }

    @Override
    public Page<FacultyDto> findAllByUniversityId(Pageable pageable, Long id) {
        return facultyRepository.findAllByUniversityId(id, pageable).map(FacultyDto::new);
    }

    @Override
    public Page<FacultyDto> findAll(Pageable pageable) {
        return facultyRepository.findAll(pageable).map(FacultyDto::new);
    }

    @Override
    public Page<FacultyDto> findAll(Pageable pageable, Specification<Faculty> specification) {
        return null;
    }

    @Override
    public void remove(Long id) {
        if (!facultyRepository.existsById(id)){
            throw new NotFoundException("error.faculty.notExistsId");
        }
        facultyRepository.deleteById(id);
    }

    @Override
    public FacultyDto save(FacultyRegistrationDto dto) {
        Faculty faculty = convert(dto);
        faculty.setUniversity(new University());
        faculty.getUniversity().setId(dto.getUniversityId());
        return new FacultyDto(facultyRepository.save(faculty));
    }

    @Override
    public void update(FacultyRegistrationDto dto) {
        Faculty faculty = convert(dto);
        faculty.setId(dto.getId());
        facultyRepository.save(faculty);
    }


    private Faculty convert(FacultyRegistrationDto dto){
        Faculty faculty = new Faculty();
        faculty.setEmail(dto.getEmail());
        faculty.setInformation(dto.getInformation());
        faculty.setName(dto.getName());
        faculty.setShortName(dto.getShortName());
        faculty.setPhone(dto.getPhone());
        return faculty;
    }
}
