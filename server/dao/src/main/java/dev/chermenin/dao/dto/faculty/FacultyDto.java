package dev.chermenin.dao.dto.faculty;

import dev.chermenin.dao.dto.user.UniversityElementDto;
import dev.chermenin.model.impl.Faculty;
import dev.chermenin.model.impl.GroupInformation;
import dev.chermenin.model.impl.University;
import dev.chermenin.dao.dto.Dto;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class FacultyDto implements Dto {
    private Long id;
    private String email;
    private String phone;
    private String name;
    private String shortName;
    private String information;
    private UniversityElementDto university;

    public FacultyDto(Long id, String email, String phone, String name, String shortName, String information, UniversityElementDto university) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.shortName = shortName;
        this.information = information;
        this.university = university;
    }

    public FacultyDto(Faculty faculty) {
        this.id = faculty.getId();
        this.email = faculty.getEmail();
        this.phone = faculty.getPhone();
        this.name = faculty.getName();
        this.shortName = faculty.getShortName();
        this.information = faculty.getInformation();
        this.university = new UniversityElementDto(faculty.getUniversity());
    }
}
