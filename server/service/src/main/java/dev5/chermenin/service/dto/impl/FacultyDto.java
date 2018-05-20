package dev5.chermenin.service.dto.impl;

import dev5.chermenin.model.entity.impl.Faculty;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.model.entity.impl.University;
import dev5.chermenin.service.dto.Dto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class FacultyDto extends Dto {
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String name;
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String information;
    @NotNull
    private ComponentUniversityDto universityDto;
    private List<GroupDto> groups;


    public FacultyDto(Faculty faculty) {
        this.setId(faculty.getId());
        name = faculty.getName();
        information = faculty.getInformation();
        universityDto = new ComponentUniversityDto(faculty.getUniversity());

        if (faculty.getGroups() != null) {
            groups = faculty.getGroups().stream()
                    .map(GroupDto::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    @Setter
    public static class ComponentUniversityDto extends Dto{
        private String name;
        public ComponentUniversityDto(University dto) {
            this.name = dto.getName();
            this.setId(dto.getId());
        }
    }

}

