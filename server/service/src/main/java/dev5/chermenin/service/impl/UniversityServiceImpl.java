package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.UniversityRepository;
import dev5.chermenin.model.entity.impl.University;
import dev5.chermenin.service.api.UniversityService;
import dev5.chermenin.service.dto.impl.UniversityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Override
    public void addFacultyToUniversity(Long facultyId, Long universityId) {

    }

    @Override
    public void removeFacultyFromUniversity(Long facultyId, Long universityId) {

    }

    @Transactional
    @Override
    public UniversityDto findById(Long id) {
        University university = universityRepository.findOne(id);
        university.getFaculties().forEach(faculty -> faculty.setGroups(null));
        return new UniversityDto(university);
    }

    @Transactional
    @Override
    public List<UniversityDto> findAll(Pageable pageable) {

        List<University> universities = universityRepository.findAll(pageable).getContent();
        universities.forEach(university -> university.setFaculties(null));
        return universities.stream()
                .map(UniversityDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        universityRepository.delete(id);
    }

    @Override
    public void removeAll() {
        universityRepository.deleteAll();
    }

    @Override
    public UniversityDto save(UniversityDto universityDto) {
        universityDto.setFaculties(null);
        University university = new University();
        university.setName(university.getName());
        universityDto.setId(universityRepository.save(university).getId());
        return universityDto;
    }

    @Transactional
    @Override
    public void update(UniversityDto universityDto) {
        universityRepository.findOne(universityDto.getId()).setName(universityDto.getName());
    }
}
