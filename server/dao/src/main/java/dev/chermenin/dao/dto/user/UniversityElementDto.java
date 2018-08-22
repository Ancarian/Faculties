package dev.chermenin.dao.dto.user;

import dev.chermenin.dao.dto.Dto;
import dev.chermenin.model.impl.University;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UniversityElementDto implements Dto {
    private String name;
    private String shortName;
    private Long id;

    public UniversityElementDto(University university) {
        setId(university.getId());
        this.name = university.getName();
        this.shortName = university.getShortName();
    }

    public UniversityElementDto(String name, String shortName, Long id) {
        this.name = name;
        this.shortName = shortName;
        this.id = id;
    }
}