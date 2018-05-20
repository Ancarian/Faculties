package dev5.chermenin.service.dto.impl;

import dev5.chermenin.model.entity.impl.University;
import dev5.chermenin.service.dto.Dto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UniversityDto extends Dto {

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String name;

    private Set<FacultyDto> faculties;

    public UniversityDto(University university) {
        this.setId(university.getId());
        name = university.getName();

        if (university.getFaculties() != null) {
            faculties = university.getFaculties().stream()
                    .map(FacultyDto::new)
                    .collect(Collectors.toSet());
        }

    }

}

