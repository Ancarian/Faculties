package dev.chermenin.service.api.impl;

import dev.chermenin.dao.UniversityRepository;
import dev.chermenin.dao.dto.create.UniversityRegistrationDto;
import dev.chermenin.dao.dto.university.UniversityDto;
import dev.chermenin.model.impl.University;
import dev.chermenin.model.impl.adress.City;
import dev.chermenin.service.api.UniversityService;
import dev.chermenin.service.exception.BadRequestException;
import dev.chermenin.service.exception.ConflictException;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    @Override
    public UniversityDto save(UniversityRegistrationDto universityDto) {
        if (universityRepository.existsByEmailOrPhoneOrNameOrShortName(universityDto.getEmail(),
                universityDto.getPhone(),
                universityDto.getName(),
                universityDto.getShortName())) {
            throw new ConflictException("error.university.alreadyExists");
        }
        University university = convert(universityDto);
        return new UniversityDto(universityRepository.save(university));
    }

    @Override
    public void update(UniversityRegistrationDto universityDto) {
        if (universityDto.getId() == null) {
            throw new BadRequestException("error.university.illegalId");
        }
        if (universityRepository.existsByEmailOrPhoneOrNameOrShortNameAndIdNot(universityDto.getEmail(),
                universityDto.getPhone(),
                universityDto.getName(),
                universityDto.getShortName(),
                universityDto.getId())) {
            throw new ConflictException("error.university.alreadyExists");
        }
        University university = convert(universityDto);
        university.setId(universityDto.getId());
        universityRepository.save(university);
    }

    @Override
    public UniversityDto findById(Long id) {
        return new UniversityDto(universityRepository.findById(id).orElseThrow(() ->
                new NotFoundException("error.university.NotExistsId")));
    }

    @Override
    public Page<UniversityDto> findAll(Pageable pageable) {
        return universityRepository.findAllBy(pageable).map(UniversityDto::new);
    }

    @Override
    public Page<UniversityDto> findAll(Pageable pageable, Specification<UniversityDto> specification) {
        return universityRepository.findAllBy(pageable, specification).map(UniversityDto::new);
    }

    @Override
    public void remove(Long id) {
        if (!universityRepository.existsById(id)) {
            throw new NotFoundException("error.university.NotExistsId");
        }
        universityRepository.deleteById(id);
    }

    private University convert(UniversityRegistrationDto universityDto) {
        University university = new University();
        university.setName(universityDto.getName());
        university.setShortName(universityDto.getShortName());
        university.setInformation(universityDto.getInformation());
        university.setEmail(universityDto.getEmail());
        university.setPhone(universityDto.getPhone());
        university.setAdress(universityDto.getAdress());
        university.setCity(new City());
        university.getCity().setId(universityDto.getCityId());
        return university;
    }
}
